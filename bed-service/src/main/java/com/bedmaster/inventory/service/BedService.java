package com.bedmaster.inventory.service;

import com.bedmaster.inventory.dto.BedReadinessResponseDTO;
import com.bedmaster.inventory.dto.BedRequestDTO;

import com.bedmaster.inventory.entity.Room;
import com.bedmaster.inventory.enums.RoomStatus;
import com.bedmaster.inventory.exception.RoomNotFoundException;
import com.bedmaster.inventory.repository.RoomRepository;

import com.bedmaster.inventory.dto.BedResponseDTO;
import com.bedmaster.inventory.entity.Bed;
import com.bedmaster.inventory.enums.BedStatus;
import com.bedmaster.inventory.enums.BedType;
import com.bedmaster.inventory.exception.BedNotFoundException;
import com.bedmaster.inventory.exception.InvalidStatusTransitionException;
import com.bedmaster.inventory.repository.BedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BedService {

    private final BedRepository bedRepository;
    private final RoomRepository roomRepository;


    public BedService(BedRepository bedRepository,
                      RoomRepository roomRepository
                      ) {
        this.bedRepository = bedRepository;
        this.roomRepository = roomRepository;
    }



    public List<BedResponseDTO> getBedsByRoom(Integer roomId) {
        return bedRepository.findByRoomID(roomId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BedResponseDTO updateBedStatus(Integer bedId, String status) {

        // 1️⃣ Fetch bed
        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() -> new BedNotFoundException(bedId));

        // 2️⃣ Fetch parent room
        Room room = roomRepository.findById(bed.getRoomID())
                .orElseThrow(() -> new RoomNotFoundException(bed.getRoomID()));

        // 3️⃣ Cross‑level rule
        if (room.getStatus() == RoomStatus.OOS) {
            throw new InvalidStatusTransitionException(
                    "Cannot update bed status under OOS room"
            );
        }

        // 4️⃣ Capture old status
        String oldStatus = bed.getStatus().name();

        // 5️⃣ Update status
        try {
            bed.setStatus(
                    BedStatus.valueOf(status.toUpperCase())
            );
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusTransitionException(
                    "Invalid bed status: " + status
            );
        }

        // 6️⃣ Save bed
        Bed savedBed = bedRepository.save(bed);

        // 8️⃣ Return DTO
        return convertToDto(savedBed);
    }

    public BedReadinessResponseDTO checkBedReadiness(Integer bedId) {

        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() ->
                        new BedNotFoundException(bedId)
                );

        Room room = roomRepository.findById(bed.getRoomID())
                .orElseThrow(() ->
                        new RoomNotFoundException(bed.getRoomID())
                );

        // ❌ Room is OOS
        if (room.getStatus() == RoomStatus.OOS) {
            return new BedReadinessResponseDTO(false, "ROOM_OOS");
        }

        // ❌ Bed itself is not available
        if (bed.getStatus() != BedStatus.AVAILABLE) {
            return new BedReadinessResponseDTO(false, "BED_NOT_AVAILABLE");
        }

        // ✅ Bed is ready
        return new BedReadinessResponseDTO(true, null);
    }

    public BedResponseDTO createBed(BedRequestDTO dto) {

        Room room = roomRepository.findById(dto.getRoomID())
                .orElseThrow(() ->
                        new RoomNotFoundException(dto.getRoomID())
                );

        // ✅ CROSS‑LEVEL RULE
        if (room.getStatus() == RoomStatus.OOS) {
            throw new InvalidStatusTransitionException(
                    "Cannot create bed under OOS room"
            );
        }

        Bed bed = new Bed();
        bed.setRoomID(dto.getRoomID());
        bed.setBedNumber(dto.getBedNumber());

        try {
            bed.setBedType(
                    BedType.valueOf(dto.getBedType().toUpperCase())
            );
            bed.setStatus(
                    BedStatus.valueOf(dto.getStatus().toUpperCase())
            );
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusTransitionException(
                    "Invalid bed type or status"
            );
        }

        return convertToDto(bedRepository.save(bed));
    }

    private BedResponseDTO convertToDto(Bed bed) {
        BedResponseDTO dto = new BedResponseDTO();
        dto.setBedID(bed.getBedID());
        dto.setBedNumber(bed.getBedNumber());
        dto.setBedType(bed.getBedType().name());
        dto.setStatus(bed.getStatus().name());
        return dto;
    }
}
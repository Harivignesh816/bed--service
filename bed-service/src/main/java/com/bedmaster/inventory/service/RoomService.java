package com.bedmaster.inventory.service;

import com.bedmaster.inventory.dto.RoomDTO;
import com.bedmaster.inventory.entity.Room;
import com.bedmaster.inventory.repository.RoomRepository;
import org.springframework.stereotype.Service;   // ✅ THIS WAS MISSING

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDTO> getRoomsByUnit(Integer unitId) {
        List<Room> rooms = roomRepository.findByUnitID(unitId);

        return rooms.stream()
                .map(room -> {
                    RoomDTO dto = new RoomDTO();
                    dto.setRoomID(room.getRoomID());
                    dto.setRoomNumber(room.getRoomNumber());
                    dto.setGender(room.getGender());
                    dto.setStatus(room.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    public Room updateRoomStatus(Integer roomId, String status) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!List.of("Active", "Inactive", "Maintenance").contains(status)) {
            throw new RuntimeException("Invalid room status");
        }

        room.setStatus(status);
        return roomRepository.save(room);
    }


    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }
}
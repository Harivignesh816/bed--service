package com.bedmaster.inventory.service;

import com.bedmaster.inventory.dto.BedDTO;
import com.bedmaster.inventory.entity.Bed;
import com.bedmaster.inventory.repository.BedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BedService {

    private final BedRepository bedRepository;

    public BedService(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }


    public List<BedDTO> getBedsByRoom(Integer roomId) {
        List<Bed> beds = bedRepository.findByRoomID(roomId);

        return beds.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Bed updateBedStatus(Integer bedId, String status) {

        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() -> new RuntimeException("Bed not found"));

        // ✅ Business rule
        if (!List.of("Available", "Occupied", "Cleaning", "Maintenance").contains(status)) {
            throw new RuntimeException("Invalid bed status");
        }

        bed.setStatus(status);
        return bedRepository.save(bed);
    }



    public Bed saveBed(Bed bed) {
        return bedRepository.save(bed);
    }


    private BedDTO convertToDTO(Bed bed) {
        BedDTO dto = new BedDTO();
        dto.setBedID(bed.getBedID());
        dto.setBedNumber(bed.getBedNumber());
        dto.setBedType(bed.getBedType());
        dto.setStatus(bed.getStatus());
        return dto;
    }
}
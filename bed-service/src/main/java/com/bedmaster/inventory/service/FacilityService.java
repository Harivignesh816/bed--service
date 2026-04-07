package com.bedmaster.inventory.service;

import com.bedmaster.inventory.dto.FacilityDTO;
import com.bedmaster.inventory.entity.Facility;
import com.bedmaster.inventory.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    /**
     * GET – Fetch all facilities and convert Entity → DTO
     */
    public List<FacilityDTO> getAllFacilities() {
        return facilityRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * POST – Save Facility Entity
     */
    public Facility saveFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    /**
     * Utility method to convert Entity → DTO
     */
    private FacilityDTO convertToDTO(Facility facility) {
        FacilityDTO dto = new FacilityDTO();
        dto.setFacilityID(facility.getFacilityID());
        dto.setName(facility.getName());
        dto.setCampus(facility.getCampus());
        dto.setStatus(facility.getStatus());
        return dto;
    }
}
package com.bedmaster.inventory.controller;

import com.bedmaster.inventory.dto.FacilityDTO;
import com.bedmaster.inventory.entity.Facility;
import com.bedmaster.inventory.service.FacilityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    // ✅ GET → Returns DTO
    @GetMapping
    public List<FacilityDTO> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    // ✅ POST → Accepts Entity
    @PostMapping
    public Facility createFacility(@Valid @RequestBody Facility facility) {
        return facilityService.saveFacility(facility);
    }
}
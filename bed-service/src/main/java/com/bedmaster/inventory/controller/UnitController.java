package com.bedmaster.inventory.controller;

import com.bedmaster.inventory.dto.UnitDTO;
import com.bedmaster.inventory.entity.Unit;
import com.bedmaster.inventory.dto.UnitStatusUpdateDTO;
import com.bedmaster.inventory.service.UnitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PatchMapping("/{unitId}/status")
    public Unit updateUnitStatus(
            @PathVariable Integer unitId,
            @Valid @RequestBody UnitStatusUpdateDTO request) {

        return unitService.updateUnitStatus(unitId, request.getStatus());
    }


    // ✅ GET → Returns DTO
    @GetMapping
    public List<UnitDTO> getUnitsByFacility(@RequestParam Integer facilityId) {
        return unitService.getUnitsByFacility(facilityId);
    }

    // ✅ POST → Accepts Entity
    @PostMapping
    public Unit createUnit(@Valid @RequestBody Unit unit) {
        return unitService.saveUnit(unit);
    }
}
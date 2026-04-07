package com.bedmaster.inventory.service;

import com.bedmaster.inventory.dto.UnitDTO;
import com.bedmaster.inventory.entity.Unit;
import com.bedmaster.inventory.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    // ✅ GET → Entity to DTO
    public List<UnitDTO> getUnitsByFacility(Integer facilityId) {
        return unitRepository.findByFacilityID(facilityId)
                .stream()
                .map(unit -> {
                    UnitDTO dto = new UnitDTO();
                    dto.setUnitID(unit.getUnitID());
                    dto.setName(unit.getName());
                    dto.setSpecialty(unit.getSpecialty());
                    dto.setCapacity(unit.getCapacity());
                    dto.setStatus(unit.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Unit updateUnitStatus(Integer unitId, String status) {

        Unit unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        if (!List.of("Active", "Inactive", "Maintenance").contains(status)) {
            throw new RuntimeException("Invalid unit status");
        }

        unit.setStatus(status);
        return unitRepository.save(unit);
    }


    // ✅ POST → Save Entity
    public Unit saveUnit(Unit unit) {
        return unitRepository.save(unit);
    }
}
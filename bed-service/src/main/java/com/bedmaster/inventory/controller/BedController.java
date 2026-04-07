package com.bedmaster.inventory.controller;

import com.bedmaster.inventory.dto.BedDTO;
import com.bedmaster.inventory.entity.Bed;
import com.bedmaster.inventory.service.BedService;
import com.bedmaster.inventory.dto.BedStatusUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beds")
public class BedController {

    private final BedService bedService;

    public BedController(BedService bedService) {
        this.bedService = bedService;
    }

    @PatchMapping("/{bedId}/status")
    public Bed updateBedStatus(
            @PathVariable Integer bedId,
            @Valid @RequestBody BedStatusUpdateDTO request) {

        return bedService.updateBedStatus(bedId, request.getStatus());
    }


    // ✅ GET → Returns DTO
    @GetMapping
    public List<BedDTO> getBedsByRoom(@RequestParam Integer roomId) {
        return bedService.getBedsByRoom(roomId);
    }

    // ✅ POST → Accepts Entity
    @PostMapping
    public Bed createBed(@Valid @RequestBody Bed bed) {
        return bedService.saveBed(bed);
    }
}
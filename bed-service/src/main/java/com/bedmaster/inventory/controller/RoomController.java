package com.bedmaster.inventory.controller;
import com.bedmaster.inventory.dto.RoomStatusUpdateDTO;
import com.bedmaster.inventory.dto.RoomDTO;
import com.bedmaster.inventory.entity.Room;
import com.bedmaster.inventory.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PatchMapping("/{roomId}/status")
    public Room updateRoomStatus(
            @PathVariable Integer roomId,
            @Valid @RequestBody RoomStatusUpdateDTO request) {

        return roomService.updateRoomStatus(roomId, request.getStatus());
    }


    // ✅ GET → Returns DTO
    @GetMapping
    public List<RoomDTO> getRoomsByUnit(@RequestParam Integer unitId) {
        return roomService.getRoomsByUnit(unitId);
    }

    // ✅ POST → Accepts Entity
    @PostMapping
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomService.saveRoom(room);
    }
}
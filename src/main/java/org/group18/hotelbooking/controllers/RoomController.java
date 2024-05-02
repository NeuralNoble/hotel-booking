package org.group18.hotelbooking.controllers;

import org.group18.hotelbooking.dto.RoomDTO;
import org.group18.hotelbooking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> roomDTOs = roomService.getAllRooms();
        return ResponseEntity.ok(roomDTOs);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long roomId) {
        RoomDTO roomDTO = roomService.getRoomById(roomId);
        if (roomDTO != null) {
            return ResponseEntity.ok(roomDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO createdRoomDTO = roomService.createRoom(roomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoomDTO);
    }
    @GetMapping("/{roomId}/availability")
    public ResponseEntity<Boolean> getRoomAvailability(@PathVariable Long roomId) {
        RoomDTO roomDTO = roomService.getRoomById(roomId);
        if (roomDTO != null) {
            return ResponseEntity.ok(roomDTO.isAvailability());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{roomId}/availability")
    public ResponseEntity<Void> updateRoomAvailability(@PathVariable Long roomId, @RequestParam boolean availability) {
        roomService.updateRoomAvailability(roomId, availability);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{roomId}/price")
    public ResponseEntity<Void> updateRoomPrice(@PathVariable Long roomId, @RequestBody Map<String, Double> requestBody) {
        if (requestBody.containsKey("pricePerNight")) {
            double pricePerNight = requestBody.get("pricePerNight");
            roomService.updateRoomPrice(roomId, pricePerNight);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{roomId}/price")
    public ResponseEntity<Double> getRoomPrice(@PathVariable Long roomId) {
        Double price = roomService.getRoomPrice(roomId);
        if (price != null) {
            return ResponseEntity.ok(price);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

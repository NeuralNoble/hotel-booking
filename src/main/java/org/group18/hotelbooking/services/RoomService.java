package org.group18.hotelbooking.services;

import org.group18.hotelbooking.dto.RoomDTO;
import org.group18.hotelbooking.models.Room;
import org.group18.hotelbooking.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RoomDTO getRoomById(Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        return optionalRoom.map(this::convertToDto).orElse(null);
    }

    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = convertToEntity(roomDTO);
        Room savedRoom = roomRepository.save(room);
        return convertToDto(savedRoom);
    }

    public void updateRoomAvailability(Long roomId, boolean availability) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        optionalRoom.ifPresent(room -> {
            room.setAvailability(availability);
            roomRepository.save(room);
        });
    }

    public void updateRoomPrice(Long roomId, double pricePerNight) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        optionalRoom.ifPresent(room -> {
            room.setPricePerNight(pricePerNight);
            try {
                roomRepository.save(room); // Ensure room is within a persistence context
                // Logging the update success
                System.out.println("Room price updated successfully for roomId: " + roomId);
            } catch (Exception e) {
                // Logging the error
                System.err.println("Error updating room price for roomId: " + roomId);
                e.printStackTrace();
                // Handle the exception (e.g., throw a custom exception or perform rollback)
            }
        });
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    private RoomDTO convertToDto(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        BeanUtils.copyProperties(room, roomDTO);
        return roomDTO;
    }

    private Room convertToEntity(RoomDTO roomDTO) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO, room);
        return room;
    }

    public Double getRoomPrice(Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        return optionalRoom.map(Room::getPricePerNight).orElse(null);
    }
}

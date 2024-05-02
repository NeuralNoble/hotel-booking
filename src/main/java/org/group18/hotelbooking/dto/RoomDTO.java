package org.group18.hotelbooking.dto;



import org.group18.hotelbooking.models.RoomType;

public class RoomDTO {

    private Long roomId;
    private RoomType roomType;
    private double pricePerNight;
    private boolean availability;
    private Long hotelId;

    // Constructors, Getters, and Setters

    public RoomDTO() {
    }

    public RoomDTO(Long roomId, RoomType roomType, double pricePerNight, boolean availability, Long hotelId) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.availability = availability;
        this.hotelId = hotelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}

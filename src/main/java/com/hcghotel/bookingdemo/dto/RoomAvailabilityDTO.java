package com.hcghotel.bookingdemo.dto;

import java.util.List;

public class RoomAvailabilityDTO {
    private String roomTypeId;
    private int availableToSell;


    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getAvailableToSell() {
        return availableToSell;
    }

    public void setAvailableToSell(int availableToSell) {
        this.availableToSell = availableToSell;
    }

    public RoomAvailabilityDTO() {
    }
}

package com.hcghotel.bookingdemo.models;

import jakarta.persistence.*;

@Entity(name = "room_type")
public class RoomType {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "room_type_id")
    private Long roomTypeID;

    private String typeName;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private boolean retired;

    public Long getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(Long roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

}

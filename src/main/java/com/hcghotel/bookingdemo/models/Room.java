package com.hcghotel.bookingdemo.models;

import com.hcghotel.bookingdemo.persistence.converters.LocalDateAttributeConverter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "room_id")
    private Long roomID;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "status")
    private RoomStatus status;

    private boolean retired;

    public Room() {
    }
// Getters and setters

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }
}

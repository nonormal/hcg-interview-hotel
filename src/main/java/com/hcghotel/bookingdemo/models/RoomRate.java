package com.hcghotel.bookingdemo.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity (name = "room_rate")
public class RoomRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_rate_id")
    private Long roomRateId;

    private Integer roomTypeId;

    private Integer ratePlanId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "retired")
    private boolean retired;

    public RoomRate() {
    }

    public Long getRoomRateId() {
        return roomRateId;
    }

    public void setRoomRateId(Long roomRateId) {
        this.roomRateId = roomRateId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Integer ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }
}

package com.hcghotel.bookingdemo.models;

import jakarta.persistence.*;

@Entity(name="rate_plan")
public class RatePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "rate_plan_id")
    private Long ratePlanID;

    private String planName;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private boolean isDefault;
    private boolean retired;

    public RatePlan() {
    }
// Getters and setters

    public Long getRatePlanID() {
        return ratePlanID;
    }

    public void setRatePlanID(Long ratePlanID) {
        this.ratePlanID = ratePlanID;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }
}

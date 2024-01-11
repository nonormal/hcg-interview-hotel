package com.hcghotel.bookingdemo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BookingRequestDTO {
    private Long roomType;
    private Long ratePlan;
    private String bookingNumber;
    private Double price;
    private boolean retired;
    private List<GuestDTO> guests;

    private LocalDate arrivalDate;
    private LocalDate departureDate;

    public BookingRequestDTO() {
    }

    public Long getRoomType() {
        return roomType;
    }

    public void setRoomType(Long roomType) {
        this.roomType = roomType;
    }

    public Long getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(Long ratePlan) {
        this.ratePlan = ratePlan;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public BigDecimal getPrice() {
        return BigDecimal.valueOf(price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public List<GuestDTO> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestDTO> guests) {
        this.guests = guests;
    }

    public LocalDate getArrivalDate() {
       return this.arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return this.arrivalDate;
    }
}

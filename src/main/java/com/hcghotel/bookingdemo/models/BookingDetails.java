package com.hcghotel.bookingdemo.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity (name = "booking_details")
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "booking_details_id")
    private Long bookingDetailsID;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private Date checkInDate;
    private Date checkOutDate;
    private boolean retired;

    public BookingDetails() {
    }

    // Getters and setters

    public Long getBookingDetailsID() {
        return bookingDetailsID;
    }

    public void setBookingDetailsID(Long bookingDetailsID) {
        this.bookingDetailsID = bookingDetailsID;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }
}

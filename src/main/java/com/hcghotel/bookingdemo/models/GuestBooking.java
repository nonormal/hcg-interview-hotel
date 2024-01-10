package com.hcghotel.bookingdemo.models;

import jakarta.persistence.*;
@Entity (name = "GuestBooking")
public class GuestBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guestID")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "bookingID")
    private Booking booking;

    public GuestBooking() {
    }

// Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

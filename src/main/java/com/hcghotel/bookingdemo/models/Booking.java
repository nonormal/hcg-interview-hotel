package com.hcghotel.bookingdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "booking_id")
    private Long bookingID;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "rate_plan_id")
    private RatePlan ratePlan;

    private String bookingNumber;
    private BigDecimal price;
    private boolean retired;
    @JsonIgnoreProperties("bookings")
    @ManyToMany
    @JoinTable(
            name = "guest_booking",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id")
    )
    private List<Guest> guests;
    @JsonIgnoreProperties("booking")
    @OneToMany(mappedBy = "booking")
    private List<BookingDetails> bookingDetailsList = new ArrayList<>();

    public Booking() {
    }

    // Getters and setters

    public Long getBookingID() {
        return bookingID;
    }

    public void setBookingID(Long bookingID) {
        this.bookingID = bookingID;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }



    public RatePlan getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(RatePlan ratePlan) {
        this.ratePlan = ratePlan;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
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

    public List<BookingDetails> getBookingDetails() {
        return bookingDetailsList;
    }

    public void setBookingDetails(List<BookingDetails> bookingDetails) {
        this.bookingDetailsList = bookingDetails;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public void addBookingDetails(BookingDetails bookingDetails) {
        bookingDetailsList.add(bookingDetails);
        bookingDetails.setBooking(this);
    }
    public List<BookingDetails> getBookingDetailsList() {
        return bookingDetailsList;
    }

    public void setBookingDetailsList(List<BookingDetails> bookingDetailsList) {
        this.bookingDetailsList = bookingDetailsList;
        for (BookingDetails bookingDetails : bookingDetailsList) {
            bookingDetails.setBooking(this);
        }
    }
}

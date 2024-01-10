package com.hcghotel.bookingdemo.services;

import com.hcghotel.bookingdemo.dto.BookingRequestDTO;
import com.hcghotel.bookingdemo.models.Booking;
import com.hcghotel.bookingdemo.models.Guest;
import com.hcghotel.bookingdemo.models.RatePlan;
import com.hcghotel.bookingdemo.models.RoomType;
import com.hcghotel.bookingdemo.repositories.BookingRepository;
import com.hcghotel.bookingdemo.repositories.GuestRepository;
import com.hcghotel.bookingdemo.repositories.RatePlanRepository;
import com.hcghotel.bookingdemo.repositories.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RatePlanRepository ratePlanRepository;
    private final GuestRepository guestRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomTypeRepository roomTypeRepository,
                          RatePlanRepository ratePlanRepository, GuestRepository guestRepository /* other repositories */) {
        this.bookingRepository = bookingRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.ratePlanRepository = ratePlanRepository;
        this.guestRepository = guestRepository;
    }
    public void createBooking(BookingRequestDTO bookingRequest) {
        Booking booking = new Booking();

        // Set booking properties from DTO
        booking.setBookingNumber(bookingRequest.getBookingNumber());
        booking.setPrice(bookingRequest.getPrice());
        booking.setRetired(bookingRequest.isRetired());

        // Fetch RoomType and RatePlan entities from provided IDs in DTO
        RoomType roomType = roomTypeRepository.findById(bookingRequest.getRoomType()).orElse(null);
        RatePlan ratePlan = ratePlanRepository.findById(bookingRequest.getRatePlan()).orElse(null);
        List<Guest> guests = Arrays.asList(guestRepository.findById(bookingRequest.getGuests()).orElse(null));

        // Set RoomType and RatePlan for Booking entity
        if (roomType != null && ratePlan != null && !guests.isEmpty()) {
            booking.setRoomType(roomType);
            booking.setRatePlan(ratePlan);
            
            booking.setGuests(guests);
        } else {
            // Handle if RoomType or RatePlan is not found
        }


        bookingRepository.save(booking);
    }

}

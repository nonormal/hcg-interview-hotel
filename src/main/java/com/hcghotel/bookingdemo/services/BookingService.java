package com.hcghotel.bookingdemo.services;

import com.hcghotel.bookingdemo.dto.BookingRequestDTO;
import com.hcghotel.bookingdemo.dto.GuestDTO;
import com.hcghotel.bookingdemo.models.*;
import com.hcghotel.bookingdemo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomRateRepository roomRateRepository;
    private final RatePlanRepository ratePlanRepository;
    private final GuestRepository guestRepository;
    private final RoomService roomService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomTypeRepository roomTypeRepository,
                          RatePlanRepository ratePlanRepository, GuestRepository guestRepository, RoomRateRepository roomRateRepository,RoomService roomService) {
        this.bookingRepository = bookingRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.ratePlanRepository = ratePlanRepository;
        this.guestRepository = guestRepository;
        this.roomRateRepository = roomRateRepository;
        this.roomService = roomService;
    }
    public void createBooking(BookingRequestDTO bookingRequest) throws Exception {
        if (!roomService.isRoomTypeAvailable(bookingRequest.getRoomType(), bookingRequest.getArrivalDate(), bookingRequest.getDepartureDate())) {
            throw new Exception("Selected room type is not available for the given dates");
        }

        BigDecimal totalPrice = calculateTotalPrice(bookingRequest.getRoomType(), bookingRequest.getArrivalDate(), bookingRequest.getDepartureDate());

        validateGuests(bookingRequest.getGuests());

        Booking booking = new Booking();

        booking.setPrice(totalPrice);

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setCheckInDate(bookingRequest.getArrivalDate());
        bookingDetails.setCheckOutDate(bookingRequest.getDepartureDate());
        bookingDetails.setRetired(false); // Assuming initial value is false

        // Set the booking for the details
        bookingDetails.setBooking(booking);

        // Add booking details to the booking
        booking.addBookingDetails(bookingDetails);

        bookingRepository.save(booking);

        roomService.updateRoomAvailability(bookingRequest.getRoomType(), bookingRequest.getArrivalDate(), bookingRequest.getDepartureDate());
    }

    private BigDecimal calculateTotalPrice(Long roomTypeId, LocalDate arrivalDate, LocalDate departureDate) {
        BigDecimal currentPrice = getCurrentRoomPrice(roomTypeId, arrivalDate);

        long numberOfNights = calculateNumberOfNights(arrivalDate, departureDate);

        BigDecimal totalPrice = currentPrice.multiply(BigDecimal.valueOf(numberOfNights));

        return totalPrice;
    }

    private BigDecimal getCurrentRoomPrice(Long roomTypeId, LocalDate date) {

        RoomRate roomRate = roomRateRepository.findFirstByRoomTypeIdAndDateBeforeOrderByDateDesc(roomTypeId, date);
        if (roomRate != null) {
            return roomRate.getPrice();
        } else {
            return BigDecimal.ZERO;
        }
    }

    private long calculateNumberOfNights(LocalDate arrivalDate, LocalDate departureDate) {
        return Duration.between(arrivalDate.atStartOfDay(), departureDate.atStartOfDay()).toDays();
    }

    private void validateGuests(List<GuestDTO> guests) throws Exception {
        for (GuestDTO guestDTO : guests) {
            if (guestDTO.getFirstName() == null || guestDTO.getFirstName().isEmpty() || guestDTO.getLastName() == null || guestDTO.getLastName().isEmpty()) {
                throw new Exception("Guest name cannot be empty");
            }

            if (!isValidEmail(guestDTO.getContactInfo())) {
                throw new Exception("Invalid email format for guest: " + guestDTO.getFirstName() + " " + guestDTO.getLastName());
            }
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }


}

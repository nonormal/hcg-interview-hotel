package com.hcghotel.bookingdemo.controllers;

import com.hcghotel.bookingdemo.dto.BookingRequestDTO;
import com.hcghotel.bookingdemo.models.Booking;
import com.hcghotel.bookingdemo.repositories.BookingRepository;
import com.hcghotel.bookingdemo.services.BookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> list(){
        return bookingRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Booking get(@PathVariable Long id){
        return bookingRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody BookingRequestDTO bookingRequest){
        try {

            bookingService.createBooking(bookingRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating booking");
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also need to check foreign key
        bookingRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Booking update(@PathVariable Long id, @RequestBody Booking booking) {
        //Also need to validate update data
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(booking, existingBooking, "bookingID");
        return bookingRepository.saveAndFlush(existingBooking);
    }
}

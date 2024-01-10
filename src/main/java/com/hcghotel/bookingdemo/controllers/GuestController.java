package com.hcghotel.bookingdemo.controllers;

import com.hcghotel.bookingdemo.models.Booking;
import com.hcghotel.bookingdemo.models.Guest;
import com.hcghotel.bookingdemo.models.Guest;
import com.hcghotel.bookingdemo.repositories.GuestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/guests")
public class GuestController {
    @Autowired
    private GuestRepository guestRepository;

    @GetMapping
    public List<Guest> list(){
        return guestRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Guest get(@PathVariable Long id){
        return guestRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest create(@RequestBody final Guest guest){
        return guestRepository.saveAndFlush(guest);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Guest update(@PathVariable Long id, @RequestBody Guest guest) {
        //Also need to validate update data
        Guest existingGuest = guestRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(guest, existingGuest, "guestID");
        return guestRepository.saveAndFlush(existingGuest);
    }
}

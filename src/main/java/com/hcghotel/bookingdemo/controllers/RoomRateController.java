package com.hcghotel.bookingdemo.controllers;

import com.hcghotel.bookingdemo.models.RoomRate;
import com.hcghotel.bookingdemo.services.RoomRateService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomRateController {

    private final RoomRateService roomRateService;

    public RoomRateController(RoomRateService roomRateService) {
        this.roomRateService = roomRateService;
    }

    @GetMapping("/available-room-rates")
    public ResponseEntity<List<RoomRate>> getAvailableRoomRates(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<RoomRate> availableRoomRates = roomRateService.findAvailableRoomRatesByDate(date);
        return ResponseEntity.ok(availableRoomRates);
    }
    // Other endpoints...
}
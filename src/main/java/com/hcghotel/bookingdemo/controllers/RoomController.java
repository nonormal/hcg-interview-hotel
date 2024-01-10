package com.hcghotel.bookingdemo.controllers;

import com.hcghotel.bookingdemo.models.Room;
import com.hcghotel.bookingdemo.models.RoomRate;
import com.hcghotel.bookingdemo.services.RoomRateService;
import com.hcghotel.bookingdemo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private final RoomService roomService;

    @Autowired
    private RoomRateService roomRateService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/available-rooms")
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Room> availableRooms = roomService.findAvailableRoomsByDate(date);
        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/availability")
    public ResponseEntity<Map<String, Object>> getAvailabilityByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Map<String, Object> response = new HashMap<>();
        List<Room> availableRooms = roomService.findAvailableRoomsByDate(date);
        List<RoomRate> availableRoomRates = roomRateService.findAvailableRoomRatesByDate(date);

        List<Map<String, Object>> roomAvailabilityList = new ArrayList<>();

        for (Room room : availableRooms) {
            Map<String, Object> roomAvailability = new HashMap<>();
            roomAvailability.put("roomTypeId", room.getRoomType());
            roomAvailability.put("roomID", room.getRoomID());
            roomAvailabilityList.add(roomAvailability);
        }

        List<Map<String, Object>> roomRateList = new ArrayList<>();
        for (RoomRate roomRate : availableRoomRates) {
            Map<String, Object> roomRateInfo = new HashMap<>();
            roomRateInfo.put("roomTypeId", roomRate.getRoomTypeId());
            roomRateInfo.put("ratePlanId", roomRate.getRatePlanId());
            roomRateInfo.put("price", roomRate.getPrice());
            roomRateList.add(roomRateInfo);
        }

        response.put("data", Arrays.asList(
                Map.of("date", date.toString(), "roomAvailability", roomAvailabilityList),
                Map.of("roomRate", roomRateList)
        ));

        return ResponseEntity.ok().body(response);
    }
}

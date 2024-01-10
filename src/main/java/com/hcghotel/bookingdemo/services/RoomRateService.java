package com.hcghotel.bookingdemo.services;

import com.hcghotel.bookingdemo.models.RoomRate;
import com.hcghotel.bookingdemo.repositories.RoomRateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomRateService {

    private final RoomRateRepository roomRateRepository;

    public RoomRateService(RoomRateRepository roomRateRepository) {
        this.roomRateRepository = roomRateRepository;
    }

    public List<RoomRate> findAvailableRoomRatesByDate(LocalDate date) {
        return roomRateRepository.findAvailableRoomRatesByDate(date);
    }

    // Other methods...
}

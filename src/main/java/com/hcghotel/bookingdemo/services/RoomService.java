package com.hcghotel.bookingdemo.services;

import com.hcghotel.bookingdemo.models.Room;
import com.hcghotel.bookingdemo.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAvailableRoomsByDate(LocalDate date) {
        return roomRepository.findAvailableRoomsByDate(date);
    }


    // Other methods...
}

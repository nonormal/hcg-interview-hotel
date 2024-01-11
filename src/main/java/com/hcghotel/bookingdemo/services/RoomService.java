package com.hcghotel.bookingdemo.services;

import com.hcghotel.bookingdemo.models.Room;
import com.hcghotel.bookingdemo.models.RoomStatus;
import com.hcghotel.bookingdemo.repositories.RoomRepository;
import com.hcghotel.bookingdemo.repositories.RoomStatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomStatusRepository roomStatusRepository;
    public RoomService(RoomRepository roomRepository, RoomStatusRepository roomStatusRepository) {
        this.roomRepository = roomRepository;
        this.roomStatusRepository =roomStatusRepository;
    }

    public List<Room> findAvailableRoomsByDate(LocalDate date) {
        return roomRepository.findAvailableRoomsByDate(date);
    }


    public boolean isRoomTypeAvailable(Long roomTypeId, LocalDate arrivalDate, LocalDate departureDate) {
        List<Room> unavailableRooms = roomRepository.findUnavailableRoomsByTypeAndDateRange(roomTypeId, arrivalDate, departureDate);

        return unavailableRooms.isEmpty();
    }
    public void updateRoomAvailability(Long roomTypeId, LocalDate arrivalDate, LocalDate departureDate) {
        List<Room> availableRooms = roomRepository.findAvailableRoomsByTypeAndDateRange(roomTypeId, arrivalDate, departureDate);

        for (Room room : availableRooms) {
            room.setStatus(roomStatusRepository.findRoomStatusByStatusID(2));
            roomRepository.save(room);
        }
    }
}

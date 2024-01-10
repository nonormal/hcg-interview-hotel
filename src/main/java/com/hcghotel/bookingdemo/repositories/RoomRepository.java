package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAvailableRoomsByDate(LocalDate date);
}

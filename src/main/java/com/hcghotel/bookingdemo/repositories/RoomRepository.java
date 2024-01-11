package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAvailableRoomsByDate(LocalDate date);

    @Query(value = "SELECT * FROM room WHERE room_type_id = :roomTypeId AND status <> '1' AND date BETWEEN :arrivalDate AND :departureDate", nativeQuery = true)
    List<Room> findUnavailableRoomsByTypeAndDateRange(
            @Param("roomTypeId") Long roomTypeId,
            @Param("arrivalDate") LocalDate arrivalDate,
            @Param("departureDate") LocalDate departureDate);

    @Query(value = "SELECT * FROM room WHERE room_type_id = :roomTypeId AND status = '1' AND date BETWEEN :arrivalDate AND :departureDate", nativeQuery = true)
    List<Room> findAvailableRoomsByTypeAndDateRange(
            @Param("roomTypeId") Long roomTypeId,
            @Param("arrivalDate") LocalDate arrivalDate,
            @Param("departureDate") LocalDate departureDate);
}
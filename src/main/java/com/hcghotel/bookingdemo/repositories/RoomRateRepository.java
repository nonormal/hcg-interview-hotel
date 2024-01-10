package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.RoomRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RoomRateRepository extends JpaRepository<RoomRate, Long> {
    List<RoomRate> findByRoomTypeIdAndDate(Integer roomTypeId, LocalDate date);

    List<RoomRate> findAvailableRoomRatesByDate(LocalDate date);
}

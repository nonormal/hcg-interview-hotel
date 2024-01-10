package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

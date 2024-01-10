package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.GuestBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookingRepository extends JpaRepository<GuestBooking, Long> {
}

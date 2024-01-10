package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {
}

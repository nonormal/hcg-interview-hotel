package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}

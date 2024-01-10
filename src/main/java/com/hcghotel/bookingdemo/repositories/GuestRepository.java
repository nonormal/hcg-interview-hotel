package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
}

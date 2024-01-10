package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.security.Key;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
}

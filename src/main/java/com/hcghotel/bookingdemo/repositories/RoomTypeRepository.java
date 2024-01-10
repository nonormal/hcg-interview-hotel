package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

}

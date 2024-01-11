package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {
    RoomStatus findRoomStatusByStatusID(int i);

}

package com.hcghotel.bookingdemo.repositories;

import com.hcghotel.bookingdemo.models.RatePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatePlanRepository extends JpaRepository<RatePlan, Long> {
}

package com.pickme.rides.repository;

import com.pickme.rides.entity.CarPollingRide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<CarPollingRide, String> {
}

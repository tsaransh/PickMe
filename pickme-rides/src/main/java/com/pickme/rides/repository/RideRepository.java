package com.pickme.rides.repository;

import com.pickme.rides.entity.Rides;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Rides, String> {
}

package com.pickme.user.repository;

import com.pickme.user.entity.UserCarDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarDetailsRepository extends JpaRepository<UserCarDetails, Long> {
    Optional<UserCarDetails> findByRegisterNumber(String registrationNumber);
}

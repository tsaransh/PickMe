package com.pickme.user.repository;

import com.pickme.user.entity.UserCarDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarDetailsRepository extends JpaRepository<UserCarDetails, Long> {

    // Find car details by user ID
    List<UserCarDetails> findByUserDetailsUserId(String userId);

    // Find car details by registration number
    Optional<UserCarDetails> findByRegistrationNumber(String registrationNumber);

    List<UserCarDetails> findByUserDetailsUserIdAndIsCarActive(String userId, boolean isCarActive);
}

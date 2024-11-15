package com.pickme.user.repository;

import com.pickme.user.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

    Optional<UserDetails> findByEmailAddressOrPhoneNumber(String emailAddress, String phoneNumber);

    Boolean existsByPhoneNumber(String phoneNumber);
    Boolean existsByEmailAddress(String emailAddress);

    Optional<UserDetails> findByVerificationToken(String token);
}

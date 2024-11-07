package com.pickme.rides.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rides")
public class RidesController {

    @PostMapping("/register")
    public ResponseEntity<String> createRide() {
        return ResponseEntity.ok("Ride has been created");
    }

    @GetMapping("/info")
    public ResponseEntity<String> rideDetail(String rideId) {
        return ResponseEntity.ok("Ride Details");
    }

    @GetMapping("/book-ride")
    public ResponseEntity<String> bookARide(String rideId, String userId) {
        return ResponseEntity.ok("Ride has been booked");
    }

    @DeleteMapping("/remove-ride")
    public ResponseEntity<String> removeRide(String currentUserId, String rideId) {
        return ResponseEntity.ok("Ride has been removed");
    }

    @GetMapping("/cancel-ride")
    public ResponseEntity<String> cancelRide(String rideId, String currentUserId) {
        return ResponseEntity.ok("Ride has been canceled");
    }

    @GetMapping("/find")
    public ResponseEntity<List<String>> findARide(String startingPoint, String endingPoint) {
        List<String> locations = new ArrayList<>();
        locations.add(startingPoint);
        locations.add(endingPoint);
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/find/user")
    public ResponseEntity<?> findRideByUser() {
        return null;
    }


}

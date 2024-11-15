package com.pickme.user.controller;

import com.pickme.user.payload.CarRegisterRequest;
import com.pickme.user.payload.CarUpdateRequest;
import com.pickme.user.payload.UserCarDetailsResponse;
import com.pickme.user.service.implementation.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/register")
    public ResponseEntity<UserCarDetailsResponse> registerCar(@RequestBody CarRegisterRequest request) {
        return new ResponseEntity<>(carService.registerCar(request), HttpStatus.CREATED);
    }

    @GetMapping("/info")
    public ResponseEntity<List<UserCarDetailsResponse>> getCarInfo(@RequestParam String userId) {
        List<UserCarDetailsResponse> carData = carService.getUserCars(userId);
        return ResponseEntity.ok(carData);
    }


    @GetMapping("/number")
    public ResponseEntity<UserCarDetailsResponse> getCarInfoByRegistrationNUmber(@RequestParam String registrationNumber) {
        return ResponseEntity.ok(carService.getCarInfo(registrationNumber));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeCar(@RequestParam String currentUserId, @RequestParam String registrationNumber) {
        carService.deleteCar(currentUserId, registrationNumber);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/update")
    public ResponseEntity<UserCarDetailsResponse> updateCarInfo(CarUpdateRequest updateRequest) {
        return ResponseEntity.ok(carService.updateCarDetails(updateRequest));
    }



}

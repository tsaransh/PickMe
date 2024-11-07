package com.pickme.user.controller;

import com.pickme.user.payload.CarRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/car")
public class CarController {

    @PostMapping("/register")
    public ResponseEntity<String> registerCar(@RequestBody CarRegisterRequest carRegisterRequest) {
        return null;
    }

    @GetMapping("/info")
    public ResponseEntity<String> getCarInfo(@RequestParam String userId) {
        return null;
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeCar(@RequestParam String carId) {
        return null;
    }

    //: ToDO update car information
    @PutMapping("/update")
    public ResponseEntity<String> updateCarInfo() {
        return null;
    }



}

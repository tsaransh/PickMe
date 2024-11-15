package com.pickme.user.controller;

import com.pickme.user.entity.UserDetails;
import com.pickme.user.payload.RegisterUser;
import com.pickme.user.payload.UserCredentials;
import com.pickme.user.payload.UserDetailResponse;
import com.pickme.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/details")
    public ResponseEntity<UserDetailResponse> userDetails(@RequestParam String emailOrPhone) {
        return ResponseEntity.ok(userService.findUser(emailOrPhone));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody RegisterUser userData) {
        return null;
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<Void> deleteUser(@RequestBody UserCredentials credentials) {
        return null;
    }

    //: ToDo after buying Aadhaar Card API
    @PostMapping("/government/verify-document")
    public ResponseEntity<Void> verifyGovernmentDocument() {
        return null;
    }

    @GetMapping("/detail")
    public ResponseEntity<UserDetailResponse> findUserByID(@RequestParam("userId") String userId) {
        return null;
    }

}

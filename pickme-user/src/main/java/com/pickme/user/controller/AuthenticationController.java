package com.pickme.user.controller;

import com.pickme.user.payload.ChangePasswordRequest;
import com.pickme.user.payload.RegisterUser;
import com.pickme.user.payload.UserCredentials;
import com.pickme.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UserCredentials credentials) {
        return ResponseEntity.ok("Hi there!");
    }

    @PostMapping("/signup-with-email")
    public ResponseEntity<String> createAccountUsingEmail(@RequestBody RegisterUser registerUser) {
        userService.registerUser(registerUser);
        return ResponseEntity.ok("Hi, your account is created please verify your email!");
    }

    @PostMapping("/signup-with-google")
    public ResponseEntity<String> createAccountUsingGoogle() {
        return ResponseEntity.ok("Hi, your account is created using your Google account!");
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String emailOrPhone) {
        userService.resetPassword(emailOrPhone);
        return ResponseEntity.ok("Email has been sent to registered email address.");
    }

    @GetMapping("/password/verify-token")
    public ResponseEntity<String> verifyPasswordToken(@RequestParam String token) {
        return ResponseEntity.ok("Redirect to the change password screen");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return ResponseEntity.ok("Password has been changed");
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmailAddress(@RequestParam("token") String token) {
        return ResponseEntity.ok(userService.verifyEmailAddress(token));
    }

    @GetMapping("/re-verify-email")
    public ResponseEntity<String> reVerifyEmailAddress(@RequestParam("emailOrPhone") String emailOrPhone) {
        return ResponseEntity.ok(userService.generateEmailVerificationToken(emailOrPhone));
    }

    @GetMapping("/block-user")
    public ResponseEntity<String> blockUser(@RequestBody RegisterUser userData) {
        return null;
    }

}

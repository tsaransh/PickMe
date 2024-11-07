package com.pickme.user.payload;

public record UserCredentials(
        String emailOrPhoneNumber,
        String password
) {
}

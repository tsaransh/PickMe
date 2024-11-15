package com.pickme.user.payload;

public record UserCredentials(
        String emailOrPhone,
        String password
) {
}

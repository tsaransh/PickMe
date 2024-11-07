package com.pickme.user.payload;

public record RegisterUser(
        String firstName,
        String lastName,
        String phoneNumber,
        String emailAddress,
        String password
) {
}

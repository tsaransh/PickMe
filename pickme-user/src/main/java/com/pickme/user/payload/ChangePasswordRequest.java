package com.pickme.user.payload;

public record ChangePasswordRequest(
        String emailOrPhone,
        int otp,
        String newPassword
) {
}

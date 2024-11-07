package com.pickme.user.payload;

import java.util.Date;

public record UserDetailResponse(
        String userId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Date createDate,
        String userProfileUrl,
        boolean isAccountVerify,
        String accountStatus,
        double userRatting,
        UserCarDetailsResponse carDetails
) {
}

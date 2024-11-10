package com.pickme.user.payload;

import java.util.Date;
import java.util.List;

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
        List<UserCarDetailsResponse> carDetails
) {
}

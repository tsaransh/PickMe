package com.pickme.user.service;

import com.pickme.user.entity.UserDetails;
import com.pickme.user.payload.ChangePasswordRequest;
import com.pickme.user.payload.RegisterUser;
import com.pickme.user.payload.UserCredentials;
import com.pickme.user.payload.UserDetailResponse;


public interface UserService {

    public String authenticateUser(UserCredentials credentials);
    public UserDetails registerUser(RegisterUser registerUser);

    public String verifyEmailAddress(String token);

    public UserDetailResponse findUser(String emailOrPhone);

    public boolean existsByEmail(String email);
    public boolean existsByPhoneNumber(String phoneNumber);

    public void changePassword(ChangePasswordRequest request);
    public void resetPassword(String email);

    public void changePasswordUsingToken(String token);

    String generateEmailVerificationToken(String emailOrPhone);
}

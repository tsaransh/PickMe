package com.pickme.user.service.implementation;

import com.pickme.user.entity.UserCarDetails;
import com.pickme.user.entity.UserDetails;
import com.pickme.user.exception.UserException;
import com.pickme.user.payload.*;
import com.pickme.user.repository.UserDetailsRepository;
import com.pickme.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.List;


@Service
@AllArgsConstructor
public class UserServices implements UserService {

    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final Random random = new Random();

    @Override
    public String authenticateUser(UserCredentials credentials) {
        return "";
    }

    @Override
    public UserDetails registerUser(RegisterUser registerUser) {
        if(existsByEmail(registerUser.emailAddress())) {
            throw new UserException("Email is already in use!", HttpStatus.BAD_REQUEST);
        }
        if(existsByPhoneNumber(registerUser.phoneNumber())) {
            throw new UserException("Mobile number is already in use!", HttpStatus.BAD_REQUEST);
        }

        UserDetails userDetails = UserDetails.builder()
                .userId(generateUniqueId())
                .userFirstName(registerUser.firstName())
                .userLastName(registerUser.lastName())
                .phoneNumber(registerUser.phoneNumber())
                .emailAddress(registerUser.emailAddress())
                .password(passwordEncoder.encode(registerUser.password()))
                .accountStatus("ACTIVE")
                .userRatting(3.5)
                .isUserAccountBlocked(false)
                .build();

        userDetails.setVerificationToken(createVerificationToken());

        UserDetails savedUser =  userDetailsRepository.save(userDetails);

        if(!savedUser.getUserId().isEmpty()) {
            emailService.sendEmailVerification(
                    savedUser.getUserFirstName(),
                    savedUser.getEmailAddress(),
                    savedUser.getVerificationToken()
                    );
        }

        return savedUser;

    }

    @Override
    public String verifyEmailAddress(String token) {
        UserDetails details = userDetailsRepository.findByVerificationToken(token)
                .orElseThrow(() ->  new UserException("no user found", HttpStatus.NOT_FOUND));

        details.setAccountVerify(true);
        details.setVerificationToken(null);

        userDetailsRepository.save(details);

        return "Account has been verified";
    }

    @Override
    public UserDetailResponse findUser(String emailOrPhone) {
        UserDetails details = getUserData(emailOrPhone, emailOrPhone);
        if(details.isUserAccountBlocked() || details.getAccountStatus().compareTo("ACTIVE") != 0) {
            throw new UserException("Your account is temporally blocked or deactivate due to some regions, please contact help and support", HttpStatus.BAD_REQUEST);
        }
        if(!details.isAccountVerify()) {
            throw new UserException("Please verify your email", HttpStatus.BAD_REQUEST);
        }

        List<UserCarDetailsResponse> carDetailsResponse = details.getCarDetails()
                .stream()
                .filter(UserCarDetails::isCarActive)
                .map(this::toCarResponse)
                .toList();

        return userResponse(details, carDetailsResponse);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDetailsRepository.existsByEmailAddress(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userDetailsRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        UserDetails userDetails = getUserData(request.emailOrPhone(), request.emailOrPhone());

        if(userDetails.getOtp() == request.otp()) {
               userDetails.setPassword(passwordEncoder.encode(request.newPassword()));
               userDetails.setOtp(0);
               userDetailsRepository.save(userDetails);

               emailService.sendChangePassword(userDetails.getEmailAddress());

        } else {
            throw new UserException("Invalid Otp", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void resetPassword(String emailOrPhone) {
        UserDetails details = getUserData(emailOrPhone, emailOrPhone);


        int otp = 100000 + random.nextInt(900000);

        details.setOtp(otp);
        userDetailsRepository.save(details);

        emailService.sendOpt(details.getEmailAddress(), otp);

    }

    @Override
    public void changePasswordUsingToken(String token) {

    }

    @Override
    public String generateEmailVerificationToken(String emailOrPhone) {
        UserDetails details = getUserData(emailOrPhone, emailOrPhone);

        if(details.isAccountVerify()) {
            throw new UserException("Your email address is already verify", HttpStatus.BAD_REQUEST);
        }

        String token = createVerificationToken();
        details.setVerificationToken(token);
        details.setAccountVerify(false);

        userDetailsRepository.save(details);

        emailService.sendEmailVerification(
                details.getUserFirstName(),
                details.getEmailAddress(),
                token);

        return "Email send to "+ maskEmail(details.getEmailAddress());
    }

    @Override
    public UserDetails getUserDetails(String userId) {
        return userDetailsRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found!", HttpStatus.NOT_FOUND));
    }

    @Override
    public void saveUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    private String maskEmail(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex <= 1) { // In case email is too short to mask reasonably
            return email;
        }

        // Extract the username and domain parts
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex);

        // Mask part of the username, keeping first and last characters visible
        String maskedUsername = username.charAt(0) +
                "*".repeat(Math.max(0, username.length() - 2)) +
                username.charAt(username.length() - 1);

        return maskedUsername + domain;
    }


    private String generateUniqueId() {
        StringBuilder id = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10)); // Generates a digit between 0-9
        }
        return id.toString();
    }

    private String createVerificationToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private UserDetails getUserData(String email, String phone) {
        return userDetailsRepository.findByEmailAddressOrPhoneNumber(email, phone)
                .orElseThrow(() ->  new UserException("no user found", HttpStatus.NOT_FOUND));
    }

    private UserDetailResponse userResponse(UserDetails details, List<UserCarDetailsResponse> carDetails) {
        return new UserDetailResponse(
                details.getUserId(),
                details.getUserFirstName(),
                details.getUserLastName(),
                details.getEmailAddress(),
                details.getPhoneNumber(),
                details.getCreateDate(),
                details.getUserProfileUrl(),
                details.isAccountVerify(),
                details.getAccountStatus(),
                details.getUserRatting(),
                carDetails
        );
    }

    private UserCarDetailsResponse toCarResponse(UserCarDetails saveCar) {
        return UserCarDetailsResponse.builder()
                .carId(saveCar.getCarId())
                .registerNumber(saveCar.getRegistrationNumber())
                .modelName(saveCar.getModelName())
                .color(saveCar.getColor())
                .seatLimit(saveCar.getSeatLimit())
                .build();
    }

}

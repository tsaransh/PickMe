package com.pickme.user.service.implementation;

import com.pickme.user.entity.UserDetails;
import com.pickme.user.exception.ApiException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Date;

@Service
public class EmailService {

    @Value("${app.host.url}")
    private String applicationHostUrl;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Async
    public void sendEmailVerification(String name, String email, String token)  {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(message, true);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Email Verification");

            String verificationLink = applicationHostUrl
                    .concat("/api/v1/user/auth/verify-email?token=")
                    .concat(token);

            Context context = new Context();
            context.setVariable("name", name);
            context.setVariable("verificationLink", verificationLink);

            String htmlContent = templateEngine.process("VerificationTemplate", context);
            mimeMessageHelper.setText(htmlContent,true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email can not be send to "+email);
        }
    }


    public boolean sendResetPassword(String email) {
        return false;
    }

    public boolean sendUserDetails(UserDetails userDetails) {
        return false;
    }


    @Async
    public void sendLoginAlert(String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Login Alter || PickMe");

            Context context = new Context();

            String changePassword = applicationHostUrl
                    .concat("/api/v1/user/auth/")
                    .concat("logoutAll?email="+email);

            context.setVariable("changePassword", changePassword);

            String htmlContent = templateEngine.process("LoginAlertTemplate", context);
            helper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch(Exception e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email can not be send to "+email);
        }

    }

    public boolean sendEmailOtp(String email, int otp) {
        return false;
    }

    @Async
    public void sendChangePassword(String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Password has been changed");

            Context context = new Context();
            Date currentDate = new Date();
            context.setVariable("today", currentDate);

            String changePassword = applicationHostUrl
                    .concat("/api/v1/user/auth/")
                    .concat("logoutAll?email="+email);

            context.setVariable("changePassword", changePassword);

            String htmlContent = templateEngine.process("ChangePasswordTemplate", context);
            helper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch(Exception e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email can not be send to "+email);
        }
    }

    @Async
    public void sendOpt(String email, int otp) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("rest password || PickMe");

            Context context = new Context();
            context.setVariable("otp", otp);

            String htmlContent = templateEngine.process("ResetPasswordTemplate", context);
            helper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch(Exception ex) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email can not be send to "+email);
        }
    }
}

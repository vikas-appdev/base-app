package com.gradlic.login.service.impl;

import com.gradlic.login.exception.ApiException;
import com.gradlic.login.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.gradlic.login.utils.EmailUtils.getEmailMessage;
import static com.gradlic.login.utils.EmailUtils.getResetPassword;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    private static final String PASSWORD_RESET_REQUEST = "Reset Password Request";
    private final JavaMailSender sender;

    @Value( "${spring.mail.verify.host}")
    private String host;
    @Value( "${spring.mail.username}")
    private String fromEmail;

    @Override
    @Async
    public void sendNewAccountEmail(String name, String email, String token) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getEmailMessage(name, host, token));
            sender.send(message);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException("Unable to send email"); // do not throw exception for mail
        }
    }

    @Override
    @Async
    public void sendResetPasswordEmail(String name, String email, String token) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(PASSWORD_RESET_REQUEST);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getResetPassword(name, host, token));
            sender.send(message);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException("Unable to send email"); // do not throw exception for mail
        }
    }
}

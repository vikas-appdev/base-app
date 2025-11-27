package com.gradlic.login.event.listener;

import com.gradlic.login.event.UserEvent;
import com.gradlic.login.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final EmailService emailService;

    @EventListener
    public void onUserEvent(UserEvent userEvent){
        switch (userEvent.getType()){
            case REGISTRATION -> emailService.sendNewAccountEmail(userEvent.getUser().getFirstName(), userEvent.getUser().getEmail(), (String) userEvent.getData().get("key"));
            case RESETPASSWORD -> emailService.sendResetPasswordEmail(userEvent.getUser().getFirstName(), userEvent.getUser().getEmail(), (String) userEvent.getData().get("key"));
            default -> {}
        }
    }
}

package com.careerconnect.event;

import com.careerconnect.model.User;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationListener {

    @Async
    @EventListener
    public void handleUserRegistration(UserRegisteredEvent event) {
        User user = event.getUser();
        // Send welcome email
        // Create user profile
        // Initialize user settings
        // Log registration
        System.out.println("New user registered: " + user.getUsername());
    }
} 
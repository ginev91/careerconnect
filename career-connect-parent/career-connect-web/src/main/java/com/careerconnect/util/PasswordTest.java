package com.careerconnect.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = "$2a$10$DnS3dO8fVdF9Nf3v3rr8Fe1dGZL9h0Xr4Xq/dB8mDv4JuLSYvMO8G";
        
        // Test common passwords
        String[] testPasswords = {
            "admin",
            "password",
            "admin123",
            "admin@123",
            "Admin@123",
            "admin1234",
            "admin@1234",
            "Admin@1234",
            "admin!@#",
            "Admin!@#"
        };
        
        for (String password : testPasswords) {
            if (encoder.matches(password, hash)) {
                System.out.println("Found matching password: " + password);
                return;
            }
        }
        
        System.out.println("No matching password found in the test set.");
    }
} 
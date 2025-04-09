package com.careerconnect.config;

import com.cloudinary.Cloudinary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryConfig.class);

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        try {
            if (cloudName == null || cloudName.isEmpty() ||
                apiKey == null || apiKey.isEmpty() ||
                apiSecret == null || apiSecret.isEmpty()) {
                throw new IllegalArgumentException("Cloudinary configuration is incomplete");
            }

            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", cloudName);
            config.put("api_key", apiKey);
            config.put("api_secret", apiSecret);
            config.put("secure", "true"); // Always use HTTPS

            Cloudinary cloudinary = new Cloudinary(config);
            logger.info("Cloudinary configured successfully for cloud: {}", cloudName);
            return cloudinary;
        } catch (Exception e) {
            logger.error("Failed to configure Cloudinary: {}", e.getMessage());
            throw new RuntimeException("Failed to configure Cloudinary", e);
        }
    }
} 
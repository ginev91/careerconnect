package com.careerconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CareerConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerConnectApplication.class, args);
    }

}

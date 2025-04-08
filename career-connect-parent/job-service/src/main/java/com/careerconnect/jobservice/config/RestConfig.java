package com.careerconnect.jobservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class RestConfig {

    @Bean(name = "jobServiceRestTemplate")
    public RestTemplate jobRestTemplate() {
        return new RestTemplate();
    }

    @Configuration
    public class WebClientConfig {

        @Bean(name = "jobServiceWebClient")
        public WebClient webClient() {
            return WebClient.builder().build();
        }

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/api/**").allowedOrigins("http://localhost:8080");
                }
            };
        }
    }
}

package com.careerconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean(name = "AppWebRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Configuration
    public class WebClientConfig {

        @Bean(name = "AppWebClient")
        public WebClient webClient() {
            return WebClient.builder().build();
        }
    }
}

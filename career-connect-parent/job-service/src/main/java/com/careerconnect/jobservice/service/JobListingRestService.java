package com.careerconnect.jobservice.service;

import com.careerconnect.model.JobListing;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class JobListingRestService {

    private final WebClient webClient;

    public JobListingRestService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8081/api/jobs").build();
    }

    public List<JobListing> getAll() {
        return webClient.get()
                .retrieve()
                .bodyToMono(JobListing[].class)
                .map(Arrays::asList)
                .block(); // blocking for simplicity in controller
    }

    public JobListing save(JobListing job) {
        return webClient.post()
                .bodyValue(job)
                .retrieve()
                .bodyToMono(JobListing.class)
                .block();
    }

    public void delete(Long id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
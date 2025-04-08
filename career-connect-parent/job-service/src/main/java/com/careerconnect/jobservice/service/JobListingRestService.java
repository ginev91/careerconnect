package com.careerconnect.jobservice.service;

import com.careerconnect.careerconnectcommon.model.JobListing;
import com.careerconnect.jobservice.repository.JobListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobListingRestService {

    private final JobListingRepository repo;

    public JobListingRestService(JobListingRepository repo) {
        this.repo = repo;
    }

    public List<JobListing> getAll() {
        return repo.findAll();
    }

    public JobListing save(JobListing job) {
        try {
            return repo.save(job);
        } catch (Exception e) {
            // Log the full stack trace
            e.printStackTrace();

            // Optionally, log specific info
            System.err.println("Error saving JobListing: " + e.getMessage());

            // Optional: rethrow or return null / custom fallback
            throw new RuntimeException("Failed to save job", e);
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
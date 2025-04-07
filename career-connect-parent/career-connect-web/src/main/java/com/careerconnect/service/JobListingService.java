package com.careerconnect.service;

import com.careerconnect.model.JobListing;
import com.careerconnect.repository.JobListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobListingService {

    private final JobListingRepository repo;

    public JobListingService(JobListingRepository repo) {
        this.repo = repo;
    }

    public List<JobListing> getAll() {
        return repo.findAll();
    }

    public JobListing save(JobListing job) {
        repo.save(job);
        return job;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
package com.careerconnect.repository;

import com.careerconnect.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {

}
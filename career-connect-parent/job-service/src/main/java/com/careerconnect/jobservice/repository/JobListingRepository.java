package com.careerconnect.jobservice.repository;

import com.careerconnect.careerconnectcommon.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {

}
package com.careerconnect.repository;

import com.careerconnect.model.ExperienceReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceReviewRepository extends JpaRepository<ExperienceReview, Long> {

    List<ExperienceReview> findByCompanyId(Long companyId);
}
package com.careerconnect.service;

import com.careerconnect.model.ExperienceReview;
import com.careerconnect.repository.ExperienceReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceReviewService {

    private final ExperienceReviewRepository repo;

    public ExperienceReviewService(ExperienceReviewRepository repo) {
        this.repo = repo;
    }

    public List<ExperienceReview> getByCompanyId(Long companyId) {
        return repo.findByCompanyId(companyId);
    }

    public void save(ExperienceReview review) {
        repo.save(review);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
package com.careerconnect.service;

import com.careerconnect.model.InterviewProcessStep;
import com.careerconnect.repository.InterviewProcessStepRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewProcessStepService {

    private final InterviewProcessStepRepository repo;

    public InterviewProcessStepService(InterviewProcessStepRepository repo) {
        this.repo = repo;
    }

    public List<InterviewProcessStep> getAll() {
        return repo.findAll();
    }

    public void save(InterviewProcessStep step) {
        repo.save(step);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
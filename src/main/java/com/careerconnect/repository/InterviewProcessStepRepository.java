package com.careerconnect.repository;

import com.careerconnect.model.InterviewProcessStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewProcessStepRepository extends JpaRepository<InterviewProcessStep, Long> {
}
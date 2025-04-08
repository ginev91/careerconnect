package com.careerconnect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "interview_steps")
public class InterviewProcessStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String stepName;

    private String description;

    private int stepOrder;

    @ManyToOne
    private Company company;

    public InterviewProcessStep() {}

    // Getters and Setters
    public Long getId() { return id; }

    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getStepOrder() { return stepOrder; }
    public void setStepOrder(int stepOrder) { this.stepOrder = stepOrder; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
package com.careerconnect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "experience_reviews")
public class ExperienceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @ManyToOne
    private Company company;

    @ManyToOne
    private User user;

    public ExperienceReview() {}

    // Getters and Setters
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

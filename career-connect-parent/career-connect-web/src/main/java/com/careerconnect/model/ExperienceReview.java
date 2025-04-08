package com.careerconnect.model;

import com.careerconnect.careerconnectcommon.model.Company;
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

    @Column(nullable = false)
    private int rating; // from 1 to 5

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

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}

package com.careerconnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Max;

public class LanguageStat {
    @NotBlank(message = "Language name cannot be empty")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Language name can only contain letters and spaces")
    private String name;

    @PositiveOrZero(message = "Percentage must be a positive number")
    @Max(value = 100, message = "Percentage cannot exceed 100")
    private double percentage;

    public LanguageStat() {}

    public LanguageStat(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
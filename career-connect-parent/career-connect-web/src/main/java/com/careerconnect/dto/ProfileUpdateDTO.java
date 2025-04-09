package com.careerconnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProfileUpdateDTO {
    
    @NotBlank(message = "Qualifications cannot be empty")
    @Size(min = 10, max = 1000, message = "Qualifications must be between 10 and 1000 characters")
    private String qualifications;

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
} 
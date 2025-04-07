package com.careerconnect.dto;

public class LanguageStat {
    private String name;
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
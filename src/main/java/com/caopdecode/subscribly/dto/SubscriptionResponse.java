package com.caopdecode.subscribly.dto;

import java.time.LocalDate;

public class SubscriptionResponse {
    private String userEmail;
    private String planName;
    private LocalDate startDate;
    private LocalDate endDate;

    public SubscriptionResponse(String userEmail, String planName, LocalDate startDate, LocalDate endDate) {
        this.userEmail = userEmail;
        this.planName = planName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPlanName() {
        return planName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

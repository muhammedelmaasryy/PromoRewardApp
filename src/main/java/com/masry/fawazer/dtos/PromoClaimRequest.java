package com.masry.fawazer.dtos;

import jakarta.validation.constraints.NotBlank;

public class PromoClaimRequest {
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    public PromoClaimRequest() {}

    public PromoClaimRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

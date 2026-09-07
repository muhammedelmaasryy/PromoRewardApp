package com.masry.fawazer.dtos;

public class PromoClaimResponse {
    private boolean success;
    private Integer rewardMb;
    private String message;

    public PromoClaimResponse() {}

    public PromoClaimResponse(boolean success, Integer rewardMb, String message) {
        this.success = success;
        this.rewardMb = rewardMb;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getRewardMb() {
        return rewardMb;
    }

    public void setRewardMb(Integer rewardMb) {
        this.rewardMb = rewardMb;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

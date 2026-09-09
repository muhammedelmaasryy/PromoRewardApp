package com.masry.fawazer.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GiftDTO {

    @NotNull(message = "Gift ID is required")
    private Integer giftId;

    @NotBlank(message = "Gift name is required")
    private String giftName;

    @NotNull(message = "Reward MB is required")
    @Positive(message = "Reward MB must be greater than 0")
    private Integer rewardMb;

    @NotNull(message = "Validity hours is required")
    @Positive(message = "Validity hours must be greater than 0")
    private Integer validityHours;

    public GiftDTO() {}

    public GiftDTO(Integer giftId, String giftName, Integer rewardMb, Integer validityHours) {
        this.giftId = giftId;
        this.giftName = giftName;
        this.rewardMb = rewardMb;
        this.validityHours = validityHours;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Integer getRewardMb() {
        return rewardMb;
    }

    public void setRewardMb(Integer rewardMb) {
        this.rewardMb = rewardMb;
    }

    public Integer getValidityHours() {
        return validityHours;
    }

    public void setValidityHours(Integer validityHours) {
        this.validityHours = validityHours;
    }
}

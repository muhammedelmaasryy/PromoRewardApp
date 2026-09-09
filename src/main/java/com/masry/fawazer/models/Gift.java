package com.masry.fawazer.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Gift {

    @Id
    private Integer giftId;

    @Column(nullable = false)
    private String giftName;

    @Column(nullable = false)
    private Integer rewardMb;

    @Column(nullable = false)
    private Integer validityHours;

    public Gift() {}

    public Gift(Integer giftId, String giftName, Integer rewardMb, Integer validityHours) {
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

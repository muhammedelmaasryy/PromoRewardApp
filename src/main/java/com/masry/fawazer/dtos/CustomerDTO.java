package com.masry.fawazer.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CustomerDTO {
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Segment ID is required")
    private Integer segmentId;

    private Integer dailyClaimCount;

    private LocalDateTime firstDailyClaimAt;

    private Integer monthlyClaimCount;

    private LocalDateTime firstMonthlyClaimAt;

    private Long totalRewardMb;


    public CustomerDTO() {}

    public CustomerDTO(String phoneNumber, String name, Integer segmentId) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.segmentId = segmentId;
    }

    public CustomerDTO(String phoneNumber, String name, Integer segmentId, Integer dailyClaimCount, LocalDateTime firstDailyClaimAt, Integer monthlyClaimCount, LocalDateTime firstMonthlyClaimAt, Long totalRewardMb) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.segmentId = segmentId;
        this.dailyClaimCount = dailyClaimCount;
        this.firstDailyClaimAt = firstDailyClaimAt;
        this.monthlyClaimCount = monthlyClaimCount;
        this.firstMonthlyClaimAt = firstMonthlyClaimAt;
        this.totalRewardMb = totalRewardMb;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public Integer getDailyClaimCount() {
        return dailyClaimCount;
    }

    public void setDailyClaimCount(Integer dailyClaimCount) {
        this.dailyClaimCount = dailyClaimCount;
    }

    public LocalDateTime getFirstDailyClaimAt() {
        return firstDailyClaimAt;
    }

    public void setFirstDailyClaimAt(LocalDateTime firstDailyClaimAt) {
        this.firstDailyClaimAt = firstDailyClaimAt;
    }

    public Integer getMonthlyClaimCount() {
        return monthlyClaimCount;
    }

    public void setMonthlyClaimCount(Integer monthlyClaimCount) {
        this.monthlyClaimCount = monthlyClaimCount;
    }

    public LocalDateTime getFirstMonthlyClaimAt() {
        return firstMonthlyClaimAt;
    }

    public void setFirstMonthlyClaimAt(LocalDateTime firstMonthlyClaimAt) {
        this.firstMonthlyClaimAt = firstMonthlyClaimAt;
    }

    public Long getTotalRewardMb() {
        return totalRewardMb;
    }

    public void setTotalRewardMb(Long totalRewardMb) {
        this.totalRewardMb = totalRewardMb;
    }
}

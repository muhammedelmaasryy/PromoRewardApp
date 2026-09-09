package com.masry.fawazer.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Customer {
    @Id
    private String phoneNumber;
    private String name;

    @ManyToOne
    @JoinColumn(name = "segment_id", nullable = false)
    private Segment segment;

    @Column(nullable = false)
    private Integer dailyClaimCount = 0;

    private LocalDateTime firstDailyClaimAt;

    @Column(nullable = false)
    private Integer monthlyClaimCount = 0;

    private LocalDateTime firstMonthlyClaimAt;

    @Column(nullable = false)
    private Long totalRewardMb = 0L;

    public Customer() {
    }

    public Customer(String phoneNumber, String name, Segment segment) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.segment = segment;
        this.dailyClaimCount = 0;
        this.monthlyClaimCount = 0;
        this.totalRewardMb = 0L;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Segment getSegment() { return segment; }
    public void setSegment(Segment segment) { this.segment = segment; }
    public Integer getDailyClaimCount() { return dailyClaimCount; }
    public void setDailyClaimCount(Integer dailyClaimCount) { this.dailyClaimCount = dailyClaimCount; }
    public LocalDateTime getFirstDailyClaimAt() { return firstDailyClaimAt; }
    public void setFirstDailyClaimAt(LocalDateTime firstDailyClaimAt) { this.firstDailyClaimAt = firstDailyClaimAt; }
    public Integer getMonthlyClaimCount() { return monthlyClaimCount; }
    public void setMonthlyClaimCount(Integer monthlyClaimCount) { this.monthlyClaimCount = monthlyClaimCount; }
    public LocalDateTime getFirstMonthlyClaimAt() { return firstMonthlyClaimAt; }
    public void setFirstMonthlyClaimAt(LocalDateTime firstMonthlyClaimAt) { this.firstMonthlyClaimAt = firstMonthlyClaimAt; }
    public Long getTotalRewardMb() { return totalRewardMb; }
    public void setTotalRewardMb(Long totalRewardMb) { this.totalRewardMb = totalRewardMb; }
}

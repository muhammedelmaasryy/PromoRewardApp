package com.masry.fawazer.models;

import jakarta.persistence.*;

@Entity
public class Segment {
    @Id
    private Integer segmentId;
    @Column(nullable = false)
    private Integer rewardMB;
    @Column(nullable = false)
    private Integer maxClaims;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeriodType periodType;

    public Segment() {}

    public Segment(Integer segmentId, Integer rewardMB, Integer maxClaims, PeriodType periodType) {
        this.segmentId = segmentId;
        this.rewardMB = rewardMB;
        this.maxClaims = maxClaims;
        this.periodType = periodType;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public Integer getRewardMB() {
        return rewardMB;
    }

    public void setRewardMB(Integer rewardMB) {
        this.rewardMB = rewardMB;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public Integer getMaxClaims() {
        return maxClaims;
    }

    public void setMaxClaims(Integer maxClaims) {
        this.maxClaims = maxClaims;
    }
}


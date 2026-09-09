package com.masry.fawazer.models;

import jakarta.persistence.*;

@Entity
public class Segment {

    @Id
    private Integer segmentId;

    @Column(nullable = false)
    private String segmentName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gift_id", nullable = false)
    private Gift gift;

    @Column(nullable = false)
    private Integer maxClaimsPerDay;

    @Column(nullable = false)
    private Integer maxClaimsPerMonth;

    public Segment() {}

    public Segment(Integer segmentId, String segmentName, Gift gift, Integer maxClaimsPerDay, Integer maxClaimsPerMonth) {
        this.segmentId = segmentId;
        this.segmentName = segmentName;
        this.gift = gift;
        this.maxClaimsPerDay = maxClaimsPerDay;
        this.maxClaimsPerMonth = maxClaimsPerMonth;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Integer getMaxClaimsPerDay() {
        return maxClaimsPerDay;
    }

    public void setMaxClaimsPerDay(Integer maxClaimsPerDay) {
        this.maxClaimsPerDay = maxClaimsPerDay;
    }

    public Integer getMaxClaimsPerMonth() {
        return maxClaimsPerMonth;
    }

    public void setMaxClaimsPerMonth(Integer maxClaimsPerMonth) {
        this.maxClaimsPerMonth = maxClaimsPerMonth;
    }
}

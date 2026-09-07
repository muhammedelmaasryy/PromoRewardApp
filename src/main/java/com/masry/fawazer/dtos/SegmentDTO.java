package com.masry.fawazer.dtos;

import com.masry.fawazer.models.PeriodType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SegmentDTO {
    @NotNull(message = "Segment ID is required")
    private Integer segmentId;

    @NotNull(message = "Reward MB is required")
    @Positive(message = "Reward MB must be greater than 0")
    private Integer rewardMB;

    @NotNull(message = "Max claims is required")
    @Positive(message = "Max claims must be greater than 0")
    private Integer maxClaims;

    @NotNull(message = "Period type is required (DAY, WEEK, MONTH)")
    private PeriodType periodType;

    public SegmentDTO() {}

    public SegmentDTO(Integer segmentId, Integer rewardMB, Integer maxClaims, PeriodType periodType) {
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

    public Integer getMaxClaims() {
        return maxClaims;
    }

    public void setMaxClaims(Integer maxClaims) {
        this.maxClaims = maxClaims;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }
}

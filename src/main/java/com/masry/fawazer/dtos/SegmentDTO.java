package com.masry.fawazer.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SegmentDTO {
    @NotNull(message = "Segment ID is required")
    private Integer segmentId;

    @NotBlank(message = "Segment name is required")
    private String segmentName;

    @NotNull(message = "Gift ID is required")
    private Integer giftId;

    @NotNull(message = "Max claims per day is required")
    @Positive(message = "Max claims per day must be greater than 0")
    private Integer maxClaimsPerDay;

    @NotNull(message = "Max claims per month is required")
    @Positive(message = "Max claims per month must be greater than 0")
    private Integer maxClaimsPerMonth;

    public SegmentDTO() {}

    public SegmentDTO(Integer segmentId, String segmentName, Integer giftId, Integer maxClaimsPerDay, Integer maxClaimsPerMonth) {
        this.segmentId = segmentId;
        this.segmentName = segmentName;
        this.giftId = giftId;
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

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
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

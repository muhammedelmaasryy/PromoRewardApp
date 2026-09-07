package com.masry.fawazer.models;

import com.masry.fawazer.models.Customer;
import com.masry.fawazer.models.Segment;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "promo_claims")
public class PromoClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "segment_id", nullable = false)
    private Segment segment;

    @Column(nullable = false)
    private Integer rewardMb;

    @Column(nullable = false)
    private LocalDateTime claimedAt;

    public PromoClaim() {
    }

    public PromoClaim(Long id, Customer customer, Segment segment, Integer rewardMb, LocalDateTime claimedAt) {
        this.id = id;
        this.customer = customer;
        this.segment = segment;
        this.rewardMb = rewardMb;
        this.claimedAt = claimedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Integer getRewardMb() {
        return rewardMb;
    }

    public void setRewardMb(Integer rewardMb) {
        this.rewardMb = rewardMb;
    }

    public LocalDateTime getClaimedAt() {
        return claimedAt;
    }

    public void setClaimedAt(LocalDateTime claimedAt) {
        this.claimedAt = claimedAt;
    }
}
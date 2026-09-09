package com.masry.fawazer.services;

import com.masry.fawazer.dtos.PromoClaimResponse;
import com.masry.fawazer.dtos.PromoInquiryResponse;
import com.masry.fawazer.exceptions.ClaimLimitExceededException;
import com.masry.fawazer.models.Customer;
import com.masry.fawazer.models.Gift;
import com.masry.fawazer.models.Segment;
import com.masry.fawazer.repositories.CustomerRepository;
import com.masry.fawazer.repositories.GiftRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PromoClaimService {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final GiftRepository giftRepository;

    @Autowired
    public PromoClaimService(CustomerService customerService, CustomerRepository customerRepository, GiftRepository giftRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.giftRepository = giftRepository;
    }

    public PromoInquiryResponse inquire(String phoneNumber) {
        Customer customer = customerService.getCustomerEntity(phoneNumber);
        Segment segment = customer.getSegment();
        LocalDateTime now = LocalDateTime.now();

        if (customer.getFirstMonthlyClaimAt() != null
                && now.isAfter(customer.getFirstMonthlyClaimAt().plusMonths(1))) {
            customer.setMonthlyClaimCount(0);
            customer.setFirstMonthlyClaimAt(null);
        }

        if (customer.getMonthlyClaimCount() >= segment.getMaxClaimsPerMonth()) {
            customerRepository.save(customer);
            return new PromoInquiryResponse(false,
                    "Monthly limit reached (" + segment.getMaxClaimsPerMonth() + " claim(s) per month)");
        }

        if (customer.getFirstDailyClaimAt() != null
                && now.isAfter(customer.getFirstDailyClaimAt().plusHours(24))) {
            customer.setDailyClaimCount(0);
            customer.setFirstDailyClaimAt(null);
        }

        if (customer.getDailyClaimCount() >= segment.getMaxClaimsPerDay()) {
            customerRepository.save(customer);
            return new PromoInquiryResponse(false,
                    "Daily limit reached (" + segment.getMaxClaimsPerDay() + " claim(s) per day)");
        }

        customerRepository.save(customer);
        return new PromoInquiryResponse(true, "Customer is eligible for a promo claim");
    }

    @Transactional
    public PromoClaimResponse claimPromo(String phoneNumber) {
        PromoInquiryResponse inquiry = inquire(phoneNumber);

        if (!inquiry.isEligible()) {
            throw new ClaimLimitExceededException(inquiry.getMessage());
        }

        Customer customer = customerService.getCustomerEntity(phoneNumber);
        Segment segment = customer.getSegment();
        Gift gift = segment.getGift();
        Integer rewardMb = gift != null ? gift.getRewardMb() : 0;
        LocalDateTime now = LocalDateTime.now();

        if (customer.getFirstMonthlyClaimAt() == null) {
            customer.setFirstMonthlyClaimAt(now);
        }
        if (customer.getFirstDailyClaimAt() == null) {
            customer.setFirstDailyClaimAt(now);
        }

        customer.setMonthlyClaimCount(customer.getMonthlyClaimCount() + 1);
        customer.setDailyClaimCount(customer.getDailyClaimCount() + 1);
        customer.setTotalRewardMb(customer.getTotalRewardMb() + rewardMb);

        customerRepository.save(customer);

        return new PromoClaimResponse(true, rewardMb, "Promo claimed successfully!");
    }

    public long getTotalRewardMb(String phoneNumber) {
        Customer customer = customerService.getCustomerEntity(phoneNumber);
        return customer.getTotalRewardMb() != null ? customer.getTotalRewardMb() : 0L;
    }

    public List<Gift> getAllPromos() {
        return giftRepository.findAll();
    }

    public List<Gift> getCustomersPromos(String phoneNumber) {
        Customer customer = customerService.getCustomerEntity(phoneNumber);
        if (customer.getSegment() != null && customer.getSegment().getGift() != null) {
            return List.of(customer.getSegment().getGift());
        }
        return List.of();
    }
}

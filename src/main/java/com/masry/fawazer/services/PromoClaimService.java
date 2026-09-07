package com.masry.fawazer.services;

import com.masry.fawazer.dtos.PromoClaimRequest;
import com.masry.fawazer.dtos.PromoClaimResponse;
import com.masry.fawazer.exceptions.ClaimLimitExceededException;
import com.masry.fawazer.models.Customer;
import com.masry.fawazer.models.PeriodType;
import com.masry.fawazer.models.PromoClaim;
import com.masry.fawazer.models.Segment;
import com.masry.fawazer.repositories.PromoClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

@Service
public class PromoClaimService {

    private final PromoClaimRepository promoClaimRepository;
    private final CustomerService customerService;

    @Autowired
    public PromoClaimService(PromoClaimRepository promoClaimRepository, CustomerService customerService) {
        this.promoClaimRepository = promoClaimRepository;
        this.customerService = customerService;
    }

    public PromoClaimResponse claimPromo(PromoClaimRequest request) {

        Customer customer = customerService.getCustomerEntity(request.getPhoneNumber());

        Segment segment = customer.getSegment();

        LocalDateTime windowStart = calculateWindowStart(segment.getPeriodType());

        long currentPeriodClaims = promoClaimRepository.countByCustomerAndClaimedAtGreaterThanEqual(customer, windowStart);

        if (currentPeriodClaims >= segment.getMaxClaims()) {
            throw new ClaimLimitExceededException("Customer has reached the maximum of " + segment.getMaxClaims() + 
                    " claim(s) per " + segment.getPeriodType());
        }

        PromoClaim newClaim = new PromoClaim();
        newClaim.setCustomer(customer);
        newClaim.setSegment(segment);
        newClaim.setRewardMb(segment.getRewardMB());
        newClaim.setClaimedAt(LocalDateTime.now());
        
        promoClaimRepository.save(newClaim);

        return new PromoClaimResponse(true, segment.getRewardMB(), "Promo claimed successfully!");
    }

    public long getTotalRewardMb(String phoneNumber) {
        Customer customer = customerService.getCustomerEntity(phoneNumber);
        return promoClaimRepository.findByCustomer(customer)
                .stream()
                .mapToLong(PromoClaim::getRewardMb)
                .sum();
    }

    private LocalDateTime calculateWindowStart(PeriodType periodType) {
        LocalDate today = LocalDate.now();
        LocalTime midnight = LocalTime.MIDNIGHT;

        return switch (periodType) {
            case DAY -> LocalDateTime.of(today, midnight);
            case WEEK -> {
                LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));
                yield LocalDateTime.of(startOfWeek, midnight);
            }
            case MONTH -> {
                LocalDate startOfMonth = today.withDayOfMonth(1);
                yield LocalDateTime.of(startOfMonth, midnight);
            }
        };
    }
}

package com.masry.fawazer.controllers;

import com.masry.fawazer.dtos.PromoClaimResponse;
import com.masry.fawazer.dtos.PromoInquiryResponse;
import com.masry.fawazer.models.Gift;
import com.masry.fawazer.services.PromoClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promo")
public class PromoController {

    private final PromoClaimService promoClaimService;

    @Autowired
    public PromoController(PromoClaimService promoClaimService) {
        this.promoClaimService = promoClaimService;
    }

    @GetMapping("/inquire/{phoneNumber}")
    public ResponseEntity<PromoInquiryResponse> inquirePromo(@PathVariable String phoneNumber) {
        PromoInquiryResponse response = promoClaimService.inquire(phoneNumber);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/claim/{phoneNumber}")
    public ResponseEntity<PromoClaimResponse> claimPromo(@PathVariable String phoneNumber) {
        PromoClaimResponse response = promoClaimService.claimPromo(phoneNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total/{phoneNumber}")
    public ResponseEntity<Long> getTotalRewardMb(@PathVariable String phoneNumber) {
        long totalMb = promoClaimService.getTotalRewardMb(phoneNumber);
        return ResponseEntity.ok(totalMb);
    }

    @GetMapping({"/all", "/All"})
    public ResponseEntity<List<Gift>> getAllPromoClaims() {
        return ResponseEntity.ok(promoClaimService.getAllPromos());
    }

    @GetMapping("/customer/{phoneNumber}")
    public ResponseEntity<List<Gift>> getCustomerPromoClaims(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(promoClaimService.getCustomersPromos(phoneNumber));
    }
}

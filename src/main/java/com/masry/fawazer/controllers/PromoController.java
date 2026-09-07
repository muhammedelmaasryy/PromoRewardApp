package com.masry.fawazer.controllers;

import com.masry.fawazer.dtos.PromoClaimRequest;
import com.masry.fawazer.dtos.PromoClaimResponse;
import com.masry.fawazer.services.PromoClaimService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promo")
public class PromoController {

    private final PromoClaimService promoClaimService;

    @Autowired
    public PromoController(PromoClaimService promoClaimService) {
        this.promoClaimService = promoClaimService;
    }

    @PostMapping("/claim")
    public ResponseEntity<PromoClaimResponse> claimPromo(@Valid @RequestBody PromoClaimRequest request) {
        PromoClaimResponse response = promoClaimService.claimPromo(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total/{phoneNumber}")
    public ResponseEntity<Long> getTotalRewardMb(@PathVariable String phoneNumber) {
        long totalMb = promoClaimService.getTotalRewardMb(phoneNumber);
        return ResponseEntity.ok(totalMb);
    }
}

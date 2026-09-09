package com.masry.fawazer.controllers;

import com.masry.fawazer.dtos.GiftDTO;
import com.masry.fawazer.services.GiftService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gifts")
public class GiftController {

    private final GiftService giftService;

    @Autowired
    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @PostMapping
    public ResponseEntity<GiftDTO> createGift(@Valid @RequestBody GiftDTO giftDTO) {
        GiftDTO created = giftService.createGift(giftDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GiftDTO>> getAllGifts() {
        return ResponseEntity.ok(giftService.getAllGifts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftDTO> getGift(@PathVariable Integer id) {
        return ResponseEntity.ok(giftService.getGift(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiftDTO> updateGift(@PathVariable Integer id, @Valid @RequestBody GiftDTO giftDTO) {
        return ResponseEntity.ok(giftService.updateGift(id, giftDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGift(@PathVariable Integer id) {
        giftService.deleteGift(id);
        return ResponseEntity.noContent().build();
    }
}

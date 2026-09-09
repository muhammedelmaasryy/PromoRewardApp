package com.masry.fawazer.services;

import com.masry.fawazer.dtos.GiftDTO;
import com.masry.fawazer.exceptions.GiftNotFoundException;
import com.masry.fawazer.models.Gift;
import com.masry.fawazer.repositories.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftService {

    private final GiftRepository giftRepository;

    @Autowired
    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public GiftDTO createGift(GiftDTO giftDTO) {
        Gift gift = new Gift(
                giftDTO.getGiftId(),
                giftDTO.getGiftName(),
                giftDTO.getRewardMb(),
                giftDTO.getValidityHours()
        );

        Gift savedGift = giftRepository.save(gift);

        return new GiftDTO(
                savedGift.getGiftId(),
                savedGift.getGiftName(),
                savedGift.getRewardMb(),
                savedGift.getValidityHours()
        );
    }

    public GiftDTO getGift(Integer id) {
        Gift gift = getGiftEntity(id);
        return new GiftDTO(
                gift.getGiftId(),
                gift.getGiftName(),
                gift.getRewardMb(),
                gift.getValidityHours()
        );
    }

    // For internal service-to-service use — returns the entity
    public Gift getGiftEntity(Integer id) {
        return giftRepository.findById(id)
                .orElseThrow(() -> new GiftNotFoundException("Gift with ID " + id + " not found"));
    }

    public List<GiftDTO> getAllGifts() {
        return giftRepository.findAll().stream()
                .map(gift -> new GiftDTO(
                        gift.getGiftId(),
                        gift.getGiftName(),
                        gift.getRewardMb(),
                        gift.getValidityHours()
                ))
                .toList();
    }

    public GiftDTO updateGift(Integer id, GiftDTO giftDTO) {
        Gift gift = getGiftEntity(id);

        gift.setGiftName(giftDTO.getGiftName());
        gift.setRewardMb(giftDTO.getRewardMb());
        gift.setValidityHours(giftDTO.getValidityHours());

        Gift updatedGift = giftRepository.save(gift);

        return new GiftDTO(
                updatedGift.getGiftId(),
                updatedGift.getGiftName(),
                updatedGift.getRewardMb(),
                updatedGift.getValidityHours()
        );
    }

    public void deleteGift(Integer id) {
        if (!giftRepository.existsById(id)) {
            throw new GiftNotFoundException("Gift with ID " + id + " not found");
        }
        giftRepository.deleteById(id);
    }
}

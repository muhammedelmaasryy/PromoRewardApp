package com.masry.fawazer.services;

import com.masry.fawazer.dtos.SegmentDTO;
import com.masry.fawazer.exceptions.GiftNotFoundException;
import com.masry.fawazer.exceptions.SegmentNotFoundException;
import com.masry.fawazer.models.Gift;
import com.masry.fawazer.models.Segment;
import com.masry.fawazer.repositories.GiftRepository;
import com.masry.fawazer.repositories.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegmentService {

    private final SegmentRepository segmentRepository;
    private final GiftRepository giftRepository;

    @Autowired
    public SegmentService(SegmentRepository segmentRepository, GiftRepository giftRepository) {
        this.segmentRepository = segmentRepository;
        this.giftRepository = giftRepository;
    }

    public SegmentDTO createSegment(SegmentDTO segmentDTO) {
        Gift gift = giftRepository.findById(segmentDTO.getGiftId())
                .orElseThrow(() -> new GiftNotFoundException("Gift with ID " + segmentDTO.getGiftId() + " not found"));

        Segment segment = new Segment(
                segmentDTO.getSegmentId(),
                segmentDTO.getSegmentName(),
                gift,
                segmentDTO.getMaxClaimsPerDay(),
                segmentDTO.getMaxClaimsPerMonth()
        );

        Segment savedSegment = segmentRepository.save(segment);

        return mapToDTO(savedSegment);
    }

    public SegmentDTO getSegment(Integer id) {
        Segment segment = segmentRepository.findById(id)
                .orElseThrow(() -> new SegmentNotFoundException("Segment with ID " + id + " not found"));

        return mapToDTO(segment);
    }

    public List<SegmentDTO> getAllSegments() {
        return segmentRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public SegmentDTO updateSegment(Integer id, SegmentDTO segmentDTO) {
        Segment segment = segmentRepository.findById(id)
                .orElseThrow(() -> new SegmentNotFoundException("Segment with ID " + id + " not found"));

        Gift gift = giftRepository.findById(segmentDTO.getGiftId())
                .orElseThrow(() -> new GiftNotFoundException("Gift with ID " + segmentDTO.getGiftId() + " not found"));

        segment.setSegmentName(segmentDTO.getSegmentName());
        segment.setGift(gift);
        segment.setMaxClaimsPerDay(segmentDTO.getMaxClaimsPerDay());
        segment.setMaxClaimsPerMonth(segmentDTO.getMaxClaimsPerMonth());

        Segment updatedSegment = segmentRepository.save(segment);

        return mapToDTO(updatedSegment);
    }

    public void deleteSegment(Integer id) {
        if (!segmentRepository.existsById(id)) {
            throw new SegmentNotFoundException("Segment with ID " + id + " not found");
        }
        segmentRepository.deleteById(id);
    }

    private SegmentDTO mapToDTO(Segment segment) {
        return new SegmentDTO(
                segment.getSegmentId(),
                segment.getSegmentName(),
                segment.getGift() != null ? segment.getGift().getGiftId() : null,
                segment.getMaxClaimsPerDay(),
                segment.getMaxClaimsPerMonth()
        );
    }
}
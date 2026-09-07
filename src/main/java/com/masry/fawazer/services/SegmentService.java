package com.masry.fawazer.services;

import com.masry.fawazer.dtos.SegmentDTO;
import com.masry.fawazer.exceptions.SegmentNotFoundException;
import com.masry.fawazer.models.Segment;
import com.masry.fawazer.repositories.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegmentService {

    private final SegmentRepository segmentRepository;

    @Autowired
    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public SegmentDTO createSegment(SegmentDTO segmentDTO) {
        Segment segment = new Segment(
                segmentDTO.getSegmentId(),
                segmentDTO.getRewardMB(),
                segmentDTO.getMaxClaims(),
                segmentDTO.getPeriodType()
        );
        
        Segment savedSegment = segmentRepository.save(segment);
        
        return new SegmentDTO(
                savedSegment.getSegmentId(),
                savedSegment.getRewardMB(),
                savedSegment.getMaxClaims(),
                savedSegment.getPeriodType()
        );
    }

    public SegmentDTO getSegment(Integer id) {
        Segment segment = segmentRepository.findById(id)
                .orElseThrow(() -> new SegmentNotFoundException("Segment with ID " + id + " not found"));
        
        return new SegmentDTO(
                segment.getSegmentId(),
                segment.getRewardMB(),
                segment.getMaxClaims(),
                segment.getPeriodType()
        );
    }


    public List<SegmentDTO> getAllSegments() {
        return segmentRepository.findAll().stream()
                .map(segment -> new SegmentDTO(
                        segment.getSegmentId(),
                        segment.getRewardMB(),
                        segment.getMaxClaims(),
                        segment.getPeriodType()
                ))
                .toList();
    }

    public SegmentDTO updateSegment(Integer id, SegmentDTO segmentDTO) {
        Segment segment = segmentRepository.findById(id)
                .orElseThrow(() -> new SegmentNotFoundException("Segment with ID " + id + " not found"));

        segment.setRewardMB(segmentDTO.getRewardMB());
        segment.setMaxClaims(segmentDTO.getMaxClaims());
        segment.setPeriodType(segmentDTO.getPeriodType());

        Segment updatedSegment = segmentRepository.save(segment);

        return new SegmentDTO(
                updatedSegment.getSegmentId(),
                updatedSegment.getRewardMB(),
                updatedSegment.getMaxClaims(),
                updatedSegment.getPeriodType()
        );
    }

    public void deleteSegment(Integer id) {
        if (!segmentRepository.existsById(id)) {
            throw new SegmentNotFoundException("Segment with ID " + id + " not found");
        }
        segmentRepository.deleteById(id);
    }
}
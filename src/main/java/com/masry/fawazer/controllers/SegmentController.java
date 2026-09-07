package com.masry.fawazer.controllers;

import com.masry.fawazer.dtos.SegmentDTO;
import com.masry.fawazer.services.SegmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/segments")
public class SegmentController {

    private final SegmentService segmentService;

    @Autowired
    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @PostMapping
    public ResponseEntity<SegmentDTO> createSegment(@Valid @RequestBody SegmentDTO segmentDTO) {
        SegmentDTO created = segmentService.createSegment(segmentDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SegmentDTO>> getAllSegments() {
        return ResponseEntity.ok(segmentService.getAllSegments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SegmentDTO> getSegment(@PathVariable Integer id) {
        return ResponseEntity.ok(segmentService.getSegment(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SegmentDTO> updateSegment(@PathVariable Integer id, @Valid @RequestBody SegmentDTO segmentDTO) {
        return ResponseEntity.ok(segmentService.updateSegment(id, segmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSegment(@PathVariable Integer id) {
        segmentService.deleteSegment(id);
        return ResponseEntity.noContent().build();
    }
}

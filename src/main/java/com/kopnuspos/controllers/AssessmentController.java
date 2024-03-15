package com.kopnuspos.controllers;
import com.kopnuspos.dto.request.AssessmentRequest;
import com.kopnuspos.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/assessment")
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;
    @GetMapping
    public ResponseEntity<Object> getAllAssessments() {
        return assessmentService.findAllassessmentService();
    }

    @PostMapping("")
    public ResponseEntity<Object> createdAssessment(@RequestBody AssessmentRequest assessmentRequest) {
        return assessmentService.createdassessmentService(assessmentRequest);
    }


    @GetMapping("/{assessmentId}")
    public ResponseEntity<HashMap<String, Object>> getAssessmentById(@PathVariable String assessmentId) {
        return assessmentService.findByassessmentIdService(assessmentId);
    }
    @DeleteMapping("/{assessmentId}")
    public ResponseEntity<Object> DeleteAssessmentById(@PathVariable String assessmentId) {
        return assessmentService.deleteassessmentIdService(assessmentId);
    }
    @PutMapping("/{assessmentId}")
    public ResponseEntity<Object> createdAssessment(@PathVariable String assessmentId, @RequestBody AssessmentRequest assessmentRequest) {
        return assessmentService.updateassessmentService(assessmentId, assessmentRequest);
    }



}

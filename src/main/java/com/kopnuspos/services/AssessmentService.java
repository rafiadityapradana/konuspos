package com.kopnuspos.services;

import com.kopnuspos.dto.request.AssessmentRequest;
import com.kopnuspos.entitys.AssessmentEntity;
import com.kopnuspos.entitys.UserEntity;
import com.kopnuspos.repositorys.AssessmentRepository;
import com.kopnuspos.repositorys.UserReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;


@Service
public class AssessmentService {
    @Autowired
    ResponeService responeService;
    @Autowired
    AssessmentRepository assessmentRepository;

    @Autowired
    UserReposotory userReposotory;

    public ResponseEntity<Object> findAllassessmentService() {
        return new ResponseEntity<>(responeService.GetServiceList(true, "Successfully Find All Data", assessmentRepository.findAll()), HttpStatus.OK);
    }
    public ResponseEntity<HashMap<String, Object>> findByassessmentIdService(String assessmentId) {
        try {
            Optional<AssessmentEntity> assessment = assessmentRepository.findById(UUID.fromString(assessmentId));
            return assessment.map(value -> new ResponseEntity<>(responeService.ServiceObject(true, "Successfully Find Data", value), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(responeService.ServiceObject(false, "Find Data failed", null), HttpStatus.NOT_FOUND));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(responeService.ServiceObject(false, "Find Data failed", null), HttpStatus.NOT_FOUND);
        }

    }
    public static Date stringToDate(String dateString, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateString);
    }
    public ResponseEntity<Object> createdassessmentService(AssessmentRequest assessmentRequest) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserEntity user = userReposotory.findByUserId(authentication.getName());
            AssessmentEntity assessmentEntity =  new AssessmentEntity();
            assessmentEntity.setTitle(assessmentRequest.getTitle());
            assessmentEntity.setDescription(assessmentRequest.getDescription());
            assessmentEntity.setUser(user);
            java.sql.Date sqlDate = new java.sql.Date(stringToDate(assessmentRequest.getDeadline(), "yyyy-MM-dd").getTime());
            assessmentEntity.setDeadline(sqlDate);
            assessmentEntity.setDeadline(sqlDate);
            return new ResponseEntity<>(responeService.ServiceObject(true, "Successfully Created Data", assessmentRepository.save(assessmentEntity)), HttpStatus.CREATED);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(responeService.ServiceObject(false, "Failed Created Data", assessmentRequest), HttpStatus.NO_CONTENT);
        }

    }
    public ResponseEntity<Object> deleteassessmentIdService(String assessmentId) {
        try {
            assessmentRepository.deleteById(UUID.fromString(assessmentId));
            return new ResponseEntity<>(responeService.ServiceObject(true, "Successfully Delete Data", null), HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(responeService.ServiceObject(false, "Delete Data failed", null), HttpStatus.NO_CONTENT);
        }

    }
    public ResponseEntity<Object> updateassessmentService(String assessmentId, AssessmentRequest assessmentRequest) {

        try {
            Optional<AssessmentEntity> existingAssessmentOptional = assessmentRepository.findById(UUID.fromString(assessmentId));
            if (existingAssessmentOptional.isPresent()) {
                AssessmentEntity assessmentEntity = new AssessmentEntity();
                assessmentEntity.setAssessmentId(UUID.fromString(assessmentId));
                assessmentEntity.setTitle(assessmentRequest.getTitle());
                assessmentEntity.setDescription(assessmentRequest.getDescription());
                ;
                java.sql.Date sqlDate = new java.sql.Date(stringToDate(assessmentRequest.getDeadline(), "yyyy-MM-dd").getTime());
                assessmentEntity.setDeadline(sqlDate);
                assessmentEntity.setDeadline(sqlDate);
                return new ResponseEntity<>(responeService.ServiceObject(true, "Successfully update Data", assessmentRepository.save(assessmentEntity)), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(responeService.ServiceObject(false, "Failed update Data", assessmentRequest), HttpStatus.NO_CONTENT);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(responeService.ServiceObject(false, "Failed update Data", assessmentRequest), HttpStatus.NO_CONTENT);
        }

    }

}

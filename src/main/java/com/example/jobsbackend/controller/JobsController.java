package com.example.jobsbackend.controller;

import com.example.jobsbackend.exchange.PutJobRequest;
import com.example.jobsbackend.models.JobEntity;
import com.example.jobsbackend.repositories.JobRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class JobsController {

    @Autowired
    private JobRepository jobRepository;

    @PostMapping(path = "/jobs", consumes = "application/json")
    public ResponseEntity<String> uploadJob(@RequestBody PutJobRequest jobRequest) {

        if (!isValidRequest(jobRequest)) {
            return new ResponseEntity<>("Invalid Input", HttpStatus.BAD_REQUEST);
        }

        LocalDateTime expiryDate = LocalDateTime.now().plusDays(jobRequest.getNoOfDaysToExpire());

        JobEntity jobEntity = new JobEntity(jobRequest.getJobTitle(), jobRequest.getCompany(), jobRequest.getLocation(), jobRequest.getDescription(), jobRequest.getSkills(), expiryDate);
        jobRepository.save(jobEntity);
        return new ResponseEntity<>("Job successfully uploaded. Thanks", HttpStatus.OK);
    }

    public boolean isValidRequest(PutJobRequest jobRequest) {
        boolean isValid =  jobRequest.getCompany() != null && jobRequest.getDescription() != null &&
                jobRequest.getJobTitle() != null && jobRequest.getLocation() != null && jobRequest.getSkills() != null;

        if (isValid) {
            isValid &= jobRequest.getSkills().size() <= 10;
        }

        return isValid;
    }
}

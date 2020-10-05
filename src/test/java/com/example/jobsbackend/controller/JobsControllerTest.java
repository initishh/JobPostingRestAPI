package com.example.jobsbackend.controller;

import com.example.jobsbackend.JobsbackendApplication;
import com.example.jobsbackend.exchange.PutJobRequest;
import com.example.jobsbackend.models.JobEntity;
import com.example.jobsbackend.repositories.JobRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {JobsbackendApplication.class})
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class JobsControllerTest {

    private static final String JOB_APP_ENDPOINT = "/jobs";

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private JobsController jobsController;

    @Mock
    private JobRepository jobRepository;

    @BeforeEach
    public void init()  {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void missingJobDataResultsInBadHttpRequest() throws Exception {
        MockHttpServletResponse response =  mvc.perform( MockMvcRequestBuilders
                .post("/jobs")
                .content(asJsonString(new PutJobRequest()))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void correctJobResultsInSuccessfulRequest() throws Exception {
        List<String> skills = Arrays.asList("C++", ".Net", "Java");
        MockHttpServletResponse response =  mvc.perform( MockMvcRequestBuilders
                .post("/jobs")
                .content(asJsonString(new PutJobRequest("SRE", "Apple", "Hyderabad", "Good software developer",skills, 12)))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void skillsMoreThan10ResultsInBadRequest() throws Exception {
        List<String> skills = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "L", "M");

        MockHttpServletResponse response =  mvc.perform( MockMvcRequestBuilders
                .post("/jobs")
                .content(asJsonString(new PutJobRequest("SRE", "Apple", "Hyderabad", "Good software developer",skills, 12)))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void noExpiryTimeShouldNotResultsInBadRequest() throws Exception {
        List<String> skills = Arrays.asList("G", "H", "I", "J", "L", "M");

        MockHttpServletResponse response =  mvc.perform( MockMvcRequestBuilders
                .post("/jobs")
                .content(asJsonString(new PutJobRequest("SRE", "Apple", "Hyderabad", "Good software developer",skills)))
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

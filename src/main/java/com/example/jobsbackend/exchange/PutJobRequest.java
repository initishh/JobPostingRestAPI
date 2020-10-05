package com.example.jobsbackend.exchange;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PutJobRequest {

    private String jobTitle;
    private String company;
    private String location;
    private String description;
    private List<String> skills;

    private long noOfDaysToExpire = 60;

    public PutJobRequest() {
    }

    public PutJobRequest(String jobTitle, String company, String location, String description, List<String> skills, long noOfDaysToExpire) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.description = description;
        this.skills = skills;
        this.noOfDaysToExpire = noOfDaysToExpire;
    }

    public PutJobRequest(String jobTitle, String company, String location, String description, List<String> skills) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.description = description;
        this.skills = skills;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public long getNoOfDaysToExpire() {
        return noOfDaysToExpire;
    }

    public void setNoOfDaysToExpire(long noOfDaysToExpire) {
        this.noOfDaysToExpire = noOfDaysToExpire;
    }
}

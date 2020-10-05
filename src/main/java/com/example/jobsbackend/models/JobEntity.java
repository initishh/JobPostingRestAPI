package com.example.jobsbackend.models;

import com.example.jobsbackend.utils.LocalDateTimeConverter;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class JobEntity {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String title;
    @NotNull
    private String company;
    @NotNull
    private String location;
    @NotNull
    private String description;

    @ElementCollection
    @CollectionTable(name = "job_entity_skills", joinColumns = @JoinColumn(name = "job_entity_id")) // choose the name of the DB table storing the Map<>
    @Column(name = "skills")     // choose the name of the DB column used to store the Map<> value
    @JoinColumn(name = "id")            // name of the @Id column of this entity
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private List<String> skills;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime expiryTime;

    public JobEntity() {
    }

    public JobEntity(String title, String company, String location, String description, List<String> skills, LocalDateTime expiryTime) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.skills = skills;
        this.expiryTime = expiryTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "JobEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", skills=" + skills +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
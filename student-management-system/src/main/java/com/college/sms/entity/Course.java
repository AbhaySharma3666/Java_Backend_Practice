package com.college.sms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

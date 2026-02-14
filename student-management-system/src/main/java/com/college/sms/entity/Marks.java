package com.college.sms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private Integer marks;
}

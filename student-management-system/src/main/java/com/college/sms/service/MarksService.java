package com.college.sms.service;

import com.college.sms.entity.Marks;
import java.util.List;

public interface MarksService {
    void save(Marks marks);
    List<Marks> getByStudent(Integer studentId);
    List<Marks> getByExam(Integer examId);
    List<Marks> getAll();
    Double calculateGPA(Integer studentId, Integer examId);
}

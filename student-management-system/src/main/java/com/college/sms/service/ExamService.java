package com.college.sms.service;

import com.college.sms.entity.Exam;
import java.util.List;

public interface ExamService {
    List<Exam> getAll();
    Exam getById(Integer id);
    void save(Exam exam);
    void delete(Integer id);
}

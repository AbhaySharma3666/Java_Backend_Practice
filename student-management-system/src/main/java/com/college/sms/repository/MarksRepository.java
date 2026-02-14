package com.college.sms.repository;

import com.college.sms.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Integer> {
    List<Marks> findByStudentId(Integer studentId);
    List<Marks> findByExamId(Integer examId);
    List<Marks> findByStudentIdAndExamId(Integer studentId, Integer examId);
}

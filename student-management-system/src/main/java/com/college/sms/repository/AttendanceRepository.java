package com.college.sms.repository;

import com.college.sms.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByStudentId(Integer studentId);
    List<Attendance> findBySubjectId(Integer subjectId);
    List<Attendance> findByStudentIdAndSubjectId(Integer studentId, Integer subjectId);
}

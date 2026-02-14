package com.college.sms.service;

import com.college.sms.entity.Attendance;
import java.util.List;

public interface AttendanceService {
    void save(Attendance attendance);
    List<Attendance> getByStudent(Integer studentId);
    List<Attendance> getBySubject(Integer subjectId);
    Double getAttendancePercentage(Integer studentId, Integer subjectId);
    List<Attendance> getAll();
}

package com.college.sms.service.impl;

import com.college.sms.entity.Attendance;
import com.college.sms.repository.AttendanceRepository;
import com.college.sms.service.AttendanceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository repo;

    public AttendanceServiceImpl(AttendanceRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Attendance attendance) {
        repo.save(attendance);
    }

    @Override
    public List<Attendance> getByStudent(Integer studentId) {
        return repo.findByStudentId(studentId);
    }

    @Override
    public List<Attendance> getBySubject(Integer subjectId) {
        return repo.findBySubjectId(subjectId);
    }

    @Override
    public Double getAttendancePercentage(Integer studentId, Integer subjectId) {
        List<Attendance> list = repo.findByStudentIdAndSubjectId(studentId, subjectId);
        if (list.isEmpty()) return 0.0;
        long present = list.stream().filter(a -> "Present".equals(a.getStatus())).count();
        return (present * 100.0) / list.size();
    }

    @Override
    public List<Attendance> getAll() {
        return repo.findAll();
    }
}

package com.college.sms.service.impl;

import com.college.sms.entity.Attendance;
import com.college.sms.entity.Faculty;
import com.college.sms.entity.Marks;
import com.college.sms.repository.AttendanceRepository;
import com.college.sms.repository.FacultyRepository;
import com.college.sms.repository.MarksRepository;
import com.college.sms.service.FacultyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final AttendanceRepository attendanceRepo;
    private final MarksRepository marksRepo;
    private final FacultyRepository facultyRepo;

    public FacultyServiceImpl(AttendanceRepository a, MarksRepository m, FacultyRepository f) {
        this.attendanceRepo = a;
        this.marksRepo = m;
        this.facultyRepo = f;
    }

    @Override
    public void saveAttendance(Attendance attendance) {
        attendanceRepo.save(attendance);
    }

    @Override
    public void saveMarks(Marks marks) {
        marksRepo.save(marks);
    }

    @Override
    public List<Faculty> getAll() {
        return facultyRepo.findAll();
    }

    @Override
    public Faculty getById(Integer id) {
        return facultyRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Faculty faculty) {
        facultyRepo.save(faculty);
    }

    @Override
    public void delete(Integer id) {
        facultyRepo.deleteById(id);
    }
}

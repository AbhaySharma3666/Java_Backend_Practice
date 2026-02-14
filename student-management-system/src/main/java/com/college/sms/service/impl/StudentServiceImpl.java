package com.college.sms.service.impl;

import com.college.sms.entity.Attendance;
import com.college.sms.entity.Marks;
import com.college.sms.entity.Student;
import com.college.sms.repository.AttendanceRepository;
import com.college.sms.repository.MarksRepository;
import com.college.sms.repository.StudentRepository;
import com.college.sms.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;
    private final AttendanceRepository attendanceRepo;
    private final MarksRepository marksRepo;

    public StudentServiceImpl(StudentRepository s,
                              AttendanceRepository a,
                              MarksRepository m) {
        this.studentRepo = s;
        this.attendanceRepo = a;
        this.marksRepo = m;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepo.findById(id).orElse(null);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepo.deleteById(id);
    }

    @Override
    public List<Attendance> getAttendanceByStudent(Student student) {
        return attendanceRepo.findAll()
                .stream()
                .filter(a -> a.getStudent().getId().equals(student.getId()))
                .toList();
    }

    @Override
    public List<Marks> getMarksByStudent(Student student) {
        return marksRepo.findAll()
                .stream()
                .filter(m -> m.getStudent().getId().equals(student.getId()))
                .toList();
    }
}

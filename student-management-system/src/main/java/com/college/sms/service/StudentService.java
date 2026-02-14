package com.college.sms.service;

import com.college.sms.entity.Attendance;
import com.college.sms.entity.Marks;
import com.college.sms.entity.Student;
import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    void saveStudent(Student student);
    void deleteStudent(Integer id);

    List<Attendance> getAttendanceByStudent(Student student);

    List<Marks> getMarksByStudent(Student student);
}

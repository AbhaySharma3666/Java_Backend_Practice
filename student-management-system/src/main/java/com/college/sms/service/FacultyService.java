package com.college.sms.service;

import com.college.sms.entity.Attendance;
import com.college.sms.entity.Faculty;
import com.college.sms.entity.Marks;
import java.util.List;

public interface FacultyService {

    void saveAttendance(Attendance attendance);
    void saveMarks(Marks marks);
    List<Faculty> getAll();
    Faculty getById(Integer id);
    void save(Faculty faculty);
    void delete(Integer id);
}

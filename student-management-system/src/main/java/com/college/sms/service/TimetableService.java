package com.college.sms.service;

import com.college.sms.entity.Timetable;
import java.util.List;

public interface TimetableService {
    List<Timetable> getAll();
    void save(Timetable timetable);
    void delete(Integer id);
    List<Timetable> getByDay(String day);
}

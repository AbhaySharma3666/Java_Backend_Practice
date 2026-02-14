package com.college.sms.service.impl;

import com.college.sms.entity.Timetable;
import com.college.sms.repository.TimetableRepository;
import com.college.sms.service.TimetableService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository repo;

    public TimetableServiceImpl(TimetableRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Timetable> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(Timetable timetable) {
        repo.save(timetable);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Timetable> getByDay(String day) {
        return repo.findByDay(day);
    }
}

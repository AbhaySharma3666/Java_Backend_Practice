package com.college.sms.service.impl;

import com.college.sms.entity.Exam;
import com.college.sms.repository.ExamRepository;
import com.college.sms.service.ExamService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository repo;

    public ExamServiceImpl(ExamRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Exam> getAll() {
        return repo.findAll();
    }

    @Override
    public Exam getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void save(Exam exam) {
        repo.save(exam);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

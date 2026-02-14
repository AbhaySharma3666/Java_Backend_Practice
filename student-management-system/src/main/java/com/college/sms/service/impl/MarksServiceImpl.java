package com.college.sms.service.impl;

import com.college.sms.entity.Marks;
import com.college.sms.repository.MarksRepository;
import com.college.sms.service.MarksService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository repo;

    public MarksServiceImpl(MarksRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Marks marks) {
        repo.save(marks);
    }

    @Override
    public List<Marks> getByStudent(Integer studentId) {
        return repo.findByStudentId(studentId);
    }

    @Override
    public List<Marks> getByExam(Integer examId) {
        return repo.findByExamId(examId);
    }

    @Override
    public List<Marks> getAll() {
        return repo.findAll();
    }

    @Override
    public Double calculateGPA(Integer studentId, Integer examId) {
        List<Marks> marks = repo.findByStudentIdAndExamId(studentId, examId);
        if (marks.isEmpty()) return 0.0;
        double avg = marks.stream().mapToInt(Marks::getMarks).average().orElse(0.0);
        return avg / 10.0;
    }
}

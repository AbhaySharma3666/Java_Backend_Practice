package com.college.sms.service.impl;

import com.college.sms.entity.Notice;
import com.college.sms.repository.NoticeRepository;
import com.college.sms.service.NoticeService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository repo;

    public NoticeServiceImpl(NoticeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Notice> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(Notice notice) {
        repo.save(notice);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Notice> getRecent(int limit) {
        return repo.findAll().stream().limit(limit).collect(Collectors.toList());
    }
}

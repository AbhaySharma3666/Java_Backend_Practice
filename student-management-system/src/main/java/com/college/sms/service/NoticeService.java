package com.college.sms.service;

import com.college.sms.entity.Notice;
import java.util.List;

public interface NoticeService {
    List<Notice> getAll();
    void save(Notice notice);
    void delete(Integer id);
    List<Notice> getRecent(int limit);
}

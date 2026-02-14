package com.college.sms.service;

import com.college.sms.entity.Fee;
import com.college.sms.entity.Payment;
import java.util.List;

public interface FeeService {
    List<Fee> getAll();
    List<Fee> getByStudent(Integer studentId);
    Fee getById(Integer id);
    void save(Fee fee);
    void addPayment(Payment payment);
    List<Fee> getPending();
}

package com.college.sms.service.impl;

import com.college.sms.entity.Fee;
import com.college.sms.entity.Payment;
import com.college.sms.repository.FeeRepository;
import com.college.sms.repository.PaymentRepository;
import com.college.sms.service.FeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepo;
    private final PaymentRepository paymentRepo;

    public FeeServiceImpl(FeeRepository feeRepo, PaymentRepository paymentRepo) {
        this.feeRepo = feeRepo;
        this.paymentRepo = paymentRepo;
    }

    @Override
    public List<Fee> getAll() {
        return feeRepo.findAll();
    }

    @Override
    public List<Fee> getByStudent(Integer studentId) {
        return feeRepo.findByStudentId(studentId);
    }

    @Override
    public Fee getById(Integer id) {
        return feeRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Fee fee) {
        feeRepo.save(fee);
    }

    @Override
    public void addPayment(Payment payment) {
        paymentRepo.save(payment);
    }

    @Override
    public List<Fee> getPending() {
        return feeRepo.findByStatus("Pending");
    }
}

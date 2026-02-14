package com.college.sms.repository;

import com.college.sms.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByFeeId(Integer feeId);
}

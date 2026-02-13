package com.securityLearn.Repository;

import com.securityLearn.employee.Employee;
import com.securityLearn.entity.EmployeeSignup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSignuoRepo extends JpaRepository<EmployeeSignup, Integer> {
    EmployeeSignup findByUsername(String username);
}

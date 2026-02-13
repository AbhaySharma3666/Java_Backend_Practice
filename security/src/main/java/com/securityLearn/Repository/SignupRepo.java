package com.securityLearn.Repository;

import com.securityLearn.entity.EmployeeSignup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepo extends JpaRepository<EmployeeSignup, Integer> {

}

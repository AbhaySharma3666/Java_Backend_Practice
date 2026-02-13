package com.securityLearn.Controller;

import com.securityLearn.Repository.SignupRepo;
import com.securityLearn.employee.Employee;
import com.securityLearn.entity.EmployeeSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private SignupRepo signupRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @PostMapping("/signup")
    public String signup(@RequestBody EmployeeSignup employee) {
        employee.setPassword(encoder.encode(employee.getPassword()));
        signupRepo.save(employee);
        return "Signup Successful";
    }
}

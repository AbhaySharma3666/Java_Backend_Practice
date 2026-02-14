package com.college.sms.service.impl;

import com.college.sms.entity.Role;
import com.college.sms.entity.Student;
import com.college.sms.entity.User;
import com.college.sms.repository.UserRepository;
import com.college.sms.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;

    public AdminServiceImpl(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void createStudentUser(Student student, String password) {
        User user = new User();
        user.setUsername(student.getEmail());
        user.setPassword(encoder.encode(password));
        user.setEnabled(true);
        
        Role role = new Role();
        role.setId(3);
        role.setName("STUDENT");
        user.setRoles(Set.of(role));
        
        userRepo.save(user);
    }

    @Override
    public User createUser(String username, String password, String roleName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEnabled(true);
        
        Role role = new Role();
        role.setName(roleName);
        user.setRoles(Set.of(role));
        
        return userRepo.save(user);
    }
}

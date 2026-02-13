package com.securityLearn.service;

import com.securityLearn.Repository.EmployeeSignuoRepo;
import com.securityLearn.entity.EmployeeSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService
        implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
    private EmployeeSignuoRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        EmployeeSignup emp = repo.findByUsername(username);

        if (emp == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(
                emp.getUsername(),
                emp.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    // REQUIRED by Spring Security 7
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {

        // optional: update DB password here later
        return user;
    }
}

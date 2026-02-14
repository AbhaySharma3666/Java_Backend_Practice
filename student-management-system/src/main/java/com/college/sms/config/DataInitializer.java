package com.college.sms.config;

import com.college.sms.entity.Role;
import com.college.sms.entity.User;
import com.college.sms.repository.RoleRepository;
import com.college.sms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepo, 
                                   UserRepository userRepo,
                                   BCryptPasswordEncoder encoder) {
        return args -> {
            if (roleRepo.count() == 0) {
                Role admin = new Role();
                admin.setName("ADMIN");
                roleRepo.save(admin);

                Role faculty = new Role();
                faculty.setName("FACULTY");
                roleRepo.save(faculty);

                Role student = new Role();
                student.setName("STUDENT");
                roleRepo.save(student);
            }

            if (userRepo.findByUsername("admin").isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(encoder.encode("admin123"));
                adminUser.setEnabled(true);
                adminUser.setRoles(Set.of(roleRepo.findByName("ADMIN").orElseThrow()));
                userRepo.save(adminUser);
            }
        };
    }
}

package com.college.sms.controller.admin;

import com.college.sms.entity.Enrollment;
import com.college.sms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/enrollments")
public class AdminEnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    public AdminEnrollmentController(EnrollmentService enrollmentService, StudentService studentService, CourseService courseService) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("enrollments", enrollmentService.getAll());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAll());
        return "admin/enrollments";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Enrollment enrollment) {
        enrollmentService.save(enrollment);
        return "redirect:/admin/enrollments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        enrollmentService.delete(id);
        return "redirect:/admin/enrollments";
    }
}

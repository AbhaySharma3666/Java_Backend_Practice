package com.college.sms.controller.admin;

import com.college.sms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final StudentService studentService;
    private final DepartmentService departmentService;
    private final CourseService courseService;
    private final FacultyService facultyService;

    public AdminController(StudentService studentService, 
                          DepartmentService departmentService,
                          CourseService courseService,
                          FacultyService facultyService) {
        this.studentService = studentService;
        this.departmentService = departmentService;
        this.courseService = courseService;
        this.facultyService = facultyService;
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentService.getAllStudents().size());
        model.addAttribute("totalFaculty", facultyService.getAll().size());
        model.addAttribute("totalDepartments", departmentService.getAll().size());
        model.addAttribute("totalCourses", courseService.getAll().size());
        return "admin/dashboard";
    }
}

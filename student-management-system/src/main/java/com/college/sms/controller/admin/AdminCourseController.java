package com.college.sms.controller.admin;

import com.college.sms.entity.Course;
import com.college.sms.service.CourseService;
import com.college.sms.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/courses")
public class AdminCourseController {

    private final CourseService courseService;
    private final DepartmentService departmentService;

    public AdminCourseController(CourseService courseService, DepartmentService departmentService) {
        this.courseService = courseService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        return "admin/courses";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Course course) {
        courseService.save(course);
        return "redirect:/admin/courses";
    }
}

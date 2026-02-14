package com.college.sms.controller.admin;

import com.college.sms.entity.Subject;
import com.college.sms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/subjects")
public class AdminSubjectController {

    private final SubjectService subjectService;
    private final CourseService courseService;
    private final FacultyService facultyService;

    public AdminSubjectController(SubjectService subjectService, CourseService courseService, FacultyService facultyService) {
        this.subjectService = subjectService;
        this.courseService = courseService;
        this.facultyService = facultyService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("faculties", facultyService.getAll());
        return "admin/subjects";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Subject subject) {
        subjectService.save(subject);
        return "redirect:/admin/subjects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        subjectService.delete(id);
        return "redirect:/admin/subjects";
    }
}

package com.college.sms.controller.admin;

import com.college.sms.entity.Faculty;
import com.college.sms.service.DepartmentService;
import com.college.sms.service.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/faculty")
public class AdminFacultyController {

    private final FacultyService facultyService;
    private final DepartmentService departmentService;

    public AdminFacultyController(FacultyService facultyService, DepartmentService departmentService) {
        this.facultyService = facultyService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("facultyList", facultyService.getAll());
        return "admin/faculty-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("faculty", new Faculty());
        model.addAttribute("departments", departmentService.getAll());
        return "admin/faculty-form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("faculty", facultyService.getById(id));
        model.addAttribute("departments", departmentService.getAll());
        return "admin/faculty-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Faculty faculty) {
        facultyService.save(faculty);
        return "redirect:/admin/faculty";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        facultyService.delete(id);
        return "redirect:/admin/faculty";
    }
}

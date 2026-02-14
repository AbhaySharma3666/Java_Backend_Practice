package com.college.sms.controller.admin;

import com.college.sms.entity.Department;
import com.college.sms.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/departments")
public class AdminDepartmentController {

    private final DepartmentService service;

    public AdminDepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("departments", service.getAll());
        return "admin/departments";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Department department) {
        service.save(department);
        return "redirect:/admin/departments";
    }
}

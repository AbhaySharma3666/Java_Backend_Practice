package com.college.sms.controller.admin;

import com.college.sms.entity.Exam;
import com.college.sms.service.ExamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/exams")
public class AdminExamController {

    private final ExamService examService;

    public AdminExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("exams", examService.getAll());
        return "admin/exams";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Exam exam) {
        examService.save(exam);
        return "redirect:/admin/exams";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        examService.delete(id);
        return "redirect:/admin/exams";
    }
}

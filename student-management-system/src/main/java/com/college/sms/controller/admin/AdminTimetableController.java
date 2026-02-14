package com.college.sms.controller.admin;

import com.college.sms.entity.Timetable;
import com.college.sms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/timetable")
public class AdminTimetableController {

    private final TimetableService timetableService;
    private final SubjectService subjectService;

    public AdminTimetableController(TimetableService timetableService, SubjectService subjectService) {
        this.timetableService = timetableService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("timetables", timetableService.getAll());
        model.addAttribute("subjects", subjectService.getAll());
        return "admin/timetable";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Timetable timetable) {
        timetableService.save(timetable);
        return "redirect:/admin/timetable";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        timetableService.delete(id);
        return "redirect:/admin/timetable";
    }
}

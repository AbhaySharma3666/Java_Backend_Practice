package com.college.sms.controller.admin;

import com.college.sms.entity.Attendance;
import com.college.sms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin/attendance")
public class AdminAttendanceController {

    private final AttendanceService attendanceService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    public AdminAttendanceController(AttendanceService attendanceService, StudentService studentService, SubjectService subjectService) {
        this.attendanceService = attendanceService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("attendanceList", attendanceService.getAll());
        return "admin/attendance-list";
    }

    @GetMapping("/mark")
    public String markForm(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAll());
        return "admin/mark-attendance";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer studentId, 
                      @RequestParam Integer subjectId,
                      @RequestParam String status) {
        try {
            Attendance attendance = new Attendance();
            attendance.setStudent(studentService.getStudentById(studentId));
            attendance.setSubject(subjectService.getById(subjectId));
            attendance.setDate(LocalDate.now());
            attendance.setStatus(status);
            attendanceService.save(attendance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/attendance/mark";
    }
}

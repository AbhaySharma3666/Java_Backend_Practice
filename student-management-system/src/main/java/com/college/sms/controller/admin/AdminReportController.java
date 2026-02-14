package com.college.sms.controller.admin;

import com.college.sms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/reports")
public class AdminReportController {

    private final StudentService studentService;
    private final AttendanceService attendanceService;
    private final MarksService marksService;
    private final FeeService feeService;

    public AdminReportController(StudentService studentService, AttendanceService attendanceService, 
                                MarksService marksService, FeeService feeService) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.marksService = marksService;
        this.feeService = feeService;
    }

    @GetMapping
    public String reports(Model model) {
        model.addAttribute("totalStudents", studentService.getAllStudents().size());
        model.addAttribute("totalAttendance", attendanceService.getAll().size());
        model.addAttribute("totalMarks", marksService.getAll().size());
        model.addAttribute("pendingFees", feeService.getPending().size());
        return "admin/reports";
    }

    @GetMapping("/attendance")
    public String attendanceReport(@RequestParam(required = false) Integer studentId, Model model) {
        if (studentId != null) {
            model.addAttribute("attendance", attendanceService.getByStudent(studentId));
        }
        model.addAttribute("students", studentService.getAllStudents());
        return "admin/attendance-report";
    }

    @GetMapping("/marks")
    public String marksReport(@RequestParam(required = false) Integer studentId, Model model) {
        if (studentId != null) {
            model.addAttribute("marks", marksService.getByStudent(studentId));
        }
        model.addAttribute("students", studentService.getAllStudents());
        return "admin/marks-report";
    }
}

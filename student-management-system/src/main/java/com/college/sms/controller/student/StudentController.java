package com.college.sms.controller.student;

import com.college.sms.entity.Student;
import com.college.sms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final AttendanceService attendanceService;
    private final MarksService marksService;
    private final EnrollmentService enrollmentService;

    public StudentController(StudentService studentService, AttendanceService attendanceService, 
                           MarksService marksService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.marksService = marksService;
        this.enrollmentService = enrollmentService;
    }

    private final Integer STUDENT_ID = 1;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Student student = studentService.getStudentById(STUDENT_ID);
        model.addAttribute("student", student);
        model.addAttribute("enrollments", enrollmentService.getByStudent(STUDENT_ID));
        return "student/dashboard";
    }

    @GetMapping("/attendance")
    public String attendance(Model model) {
        model.addAttribute("attendanceList", attendanceService.getByStudent(STUDENT_ID));
        return "student/attendance";
    }

    @GetMapping("/marks")
    public String marks(Model model) {
        model.addAttribute("marksList", marksService.getByStudent(STUDENT_ID));
        return "student/marks";
    }
}

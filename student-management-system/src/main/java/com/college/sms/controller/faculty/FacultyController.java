package com.college.sms.controller.faculty;

import com.college.sms.entity.Attendance;
import com.college.sms.entity.Marks;
import com.college.sms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    private final AttendanceService attendanceService;
    private final MarksService marksService;
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final ExamService examService;

    public FacultyController(AttendanceService attendanceService, MarksService marksService, 
                           SubjectService subjectService, StudentService studentService,
                           ExamService examService) {
        this.attendanceService = attendanceService;
        this.marksService = marksService;
        this.subjectService = subjectService;
        this.studentService = studentService;
        this.examService = examService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        return "faculty/dashboard";
    }

    @GetMapping("/attendance")
    public String attendanceForm(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAll());
        return "faculty/attendance";
    }

    @PostMapping("/attendance/save")
    public String saveAttendance(@RequestParam("student.id") Integer studentId,
                                @RequestParam("subject.id") Integer subjectId,
                                @RequestParam String date,
                                @RequestParam String status) {
        try {
            Attendance attendance = new Attendance();
            attendance.setStudent(studentService.getStudentById(studentId));
            attendance.setSubject(subjectService.getById(subjectId));
            attendance.setDate(java.time.LocalDate.parse(date));
            attendance.setStatus(status);
            attendanceService.save(attendance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/faculty/dashboard";
    }

    @GetMapping("/marks")
    public String marksForm(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("exams", examService.getAll());
        return "faculty/marks";
    }

    @PostMapping("/marks/save")
    public String saveMarks(@RequestParam("student.id") Integer studentId,
                           @RequestParam("subject.id") Integer subjectId,
                           @RequestParam("exam.id") Integer examId,
                           @RequestParam Integer marks) {
        try {
            Marks mark = new Marks();
            mark.setStudent(studentService.getStudentById(studentId));
            mark.setSubject(subjectService.getById(subjectId));
            mark.setExam(examService.getById(examId));
            mark.setMarks(marks);
            marksService.save(mark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/faculty/dashboard";
    }
}

package com.college.sms.controller.admin;

import com.college.sms.entity.Marks;
import com.college.sms.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/marks")
public class AdminMarksController {

    private final MarksService marksService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final ExamService examService;

    public AdminMarksController(MarksService marksService, StudentService studentService, SubjectService subjectService, ExamService examService) {
        this.marksService = marksService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.examService = examService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("marksList", marksService.getAll());
        return "admin/marks-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("exams", examService.getAll());
        return "admin/add-marks";
    }

    @PostMapping("/save")
    public String save(@RequestParam("student.id") Integer studentId,
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
        return "redirect:/admin/marks";
    }
}

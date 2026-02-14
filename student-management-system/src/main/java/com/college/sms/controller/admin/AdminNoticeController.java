package com.college.sms.controller.admin;

import com.college.sms.entity.Notice;
import com.college.sms.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin/notices")
public class AdminNoticeController {

    private final NoticeService noticeService;

    public AdminNoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("notices", noticeService.getAll());
        return "admin/notices";
    }

    @PostMapping("/save")
    public String save(@RequestParam String title, @RequestParam String description) {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setDescription(description);
        notice.setCreatedDate(LocalDate.now());
        noticeService.save(notice);
        return "redirect:/admin/notices";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        noticeService.delete(id);
        return "redirect:/admin/notices";
    }
}

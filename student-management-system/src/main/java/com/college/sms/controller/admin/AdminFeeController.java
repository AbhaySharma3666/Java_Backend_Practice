package com.college.sms.controller.admin;

import com.college.sms.entity.Fee;
import com.college.sms.entity.Payment;
import com.college.sms.service.*;
import com.college.sms.utils.QRCodeGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Controller
@RequestMapping("/admin/fees")
public class AdminFeeController {

    private final FeeService feeService;
    private final StudentService studentService;

    public AdminFeeController(FeeService feeService, StudentService studentService) {
        this.feeService = feeService;
        this.studentService = studentService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("fees", feeService.getAll());
        model.addAttribute("students", studentService.getAllStudents());
        return "admin/fees";
    }

    @PostMapping("/save")
    public String save(@RequestParam("student.id") Integer studentId,
                      @RequestParam BigDecimal amount,
                      @RequestParam String status,
                      @RequestParam(required = false) String feeType,
                      @RequestParam(required = false) String dueDate) {
        try {
            Fee fee = new Fee();
            fee.setStudent(studentService.getStudentById(studentId));
            fee.setAmount(amount);
            fee.setStatus(status);
            fee.setFeeType(feeType);
            if (dueDate != null && !dueDate.isEmpty()) {
                fee.setDueDate(java.time.LocalDate.parse(dueDate));
            }
            feeService.save(fee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/fees";
    }

    @PostMapping("/payment")
    public String addPayment(@ModelAttribute Payment payment) {
        feeService.addPayment(payment);
        return "redirect:/admin/fees";
    }

    @GetMapping("/qr/{feeId}")
    public String generateQR(@PathVariable Integer feeId, Model model) {
        try {
            Fee fee = feeService.getById(feeId);
            String paymentData = "FEE_ID:" + fee.getId() + "|STUDENT:" + fee.getStudent().getName() + 
                               "|AMOUNT:" + fee.getAmount() + "|TYPE:" + fee.getFeeType();
            String qrCode = QRCodeGenerator.generateQRCodeBase64(paymentData, 300, 300);
            model.addAttribute("qrCode", qrCode);
            model.addAttribute("fee", fee);
            return "admin/fee-qr";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/fees";
        }
    }

    @GetMapping("/receipt/{feeId}")
    public String viewReceipt(@PathVariable Integer feeId, Model model) {
        Fee fee = feeService.getById(feeId);
        model.addAttribute("fee", fee);
        return "admin/fee-receipt";
    }

    @GetMapping("/receipt/download/{feeId}")
    public org.springframework.http.ResponseEntity<byte[]> downloadReceipt(@PathVariable Integer feeId) {
        try {
            Fee fee = feeService.getById(feeId);
            byte[] pdfBytes = com.college.sms.utils.ReceiptGenerator.generateReceipt(fee);
            
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=receipt-" + fee.getId() + ".pdf");
            headers.add("Content-Type", "application/pdf");
            
            return new org.springframework.http.ResponseEntity<>(pdfBytes, headers, org.springframework.http.HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new org.springframework.http.ResponseEntity<>(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

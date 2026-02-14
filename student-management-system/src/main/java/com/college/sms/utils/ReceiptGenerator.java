package com.college.sms.utils;

import com.college.sms.entity.Fee;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptGenerator {

    public static byte[] generateReceipt(Fee fee) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("FEE RECEIPT")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER));
        
        document.add(new Paragraph("College Student Management System")
                .setTextAlignment(TextAlignment.CENTER));
        
        document.add(new Paragraph("\n"));

        Table table = new Table(2);
        table.addCell("Receipt No:");
        table.addCell("RCP-" + fee.getId());
        table.addCell("Date:");
        table.addCell(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        table.addCell("Student Name:");
        table.addCell(fee.getStudent().getName());
        table.addCell("Roll No:");
        table.addCell(fee.getStudent().getRollNo());
        table.addCell("Department:");
        table.addCell(fee.getStudent().getDepartment() != null ? fee.getStudent().getDepartment().getName() : "N/A");
        table.addCell("Fee Type:");
        table.addCell(fee.getFeeType() != null ? fee.getFeeType() : "General");
        table.addCell("Amount:");
        table.addCell("₹" + fee.getAmount());
        table.addCell("Status:");
        table.addCell(fee.getStatus());
        table.addCell("Due Date:");
        table.addCell(fee.getDueDate() != null ? fee.getDueDate().toString() : "N/A");

        document.add(table);
        
        document.add(new Paragraph("\n\nThank you for your payment!")
                .setTextAlignment(TextAlignment.CENTER));

        document.close();
        return baos.toByteArray();
    }
}

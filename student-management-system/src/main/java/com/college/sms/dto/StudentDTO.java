package com.college.sms.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Integer id;
    private String rollNo;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private Integer departmentId;
    private String departmentName;
}

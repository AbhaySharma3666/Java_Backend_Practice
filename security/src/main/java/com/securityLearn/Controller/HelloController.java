package com.securityLearn.Controller;

import com.securityLearn.employee.Employee;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    private List<Employee> emp = new ArrayList<>(List.of(
            new Employee(1,"Sonu", 25)
    ));

    @GetMapping("/")
    public String hello() {
        return "Learning Spring Security";
    }

    @GetMapping("id")
    public String id(HttpServletRequest req) {
        return "Session id : " + req.getSession().getId();
    }

    @GetMapping("/emp")
    public List<Employee> getEmp() {
        return emp;
    }
    @PostMapping("/add")
    public void addEmp(@RequestBody Employee employee) {
        emp.add(employee);
    }

    @GetMapping("/csrf")
    public CsrfToken getCrfs(HttpServletRequest req) {
        return (CsrfToken) req.getAttribute("_csrf");
    }

}

package com.thymeleaf.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/show")
    public String show(Model model) {
        String channel="Its Dynamic data";
        String condition = "Admin";
        model.addAttribute("name",channel);
        model.addAttribute("check",condition);
        return "show";
    }

    @GetMapping("/show/{age}")
    public String show(@PathVariable int age, Model model) {
        int userAge = age;
        model.addAttribute("age",userAge);
        return "age";
    }

    @GetMapping("/home")
    public String getData(Model model) {
        List<Integer> list1 = List.of(1,2,3,4,5,6,7,8,9);
        model.addAttribute("listOfNumber",list1);
        return "home";
    }

    @GetMapping("/home/{userinput}")
    public String getinput(@PathVariable("userinput") int user, Model model) {
        List<Integer> table = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            table.add(user*i);
        }
        model.addAttribute("number",user);
        model.addAttribute("table",table);
        return "home";
    }

    @GetMapping("/header")
    public String header1() {
        return "header";
    }
}

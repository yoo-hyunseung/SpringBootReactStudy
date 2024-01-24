package com.spring.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model){
        String str = "helloworld";
        model.addAttribute("abc",str);
        return "index";
    }

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("aaa","aaabb");
        return "hello";
    }
}

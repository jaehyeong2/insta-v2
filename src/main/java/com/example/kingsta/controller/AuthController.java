package com.example.kingsta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("auth/signin")
    public String home(){
        return "auth/signin";
    }

    @GetMapping("auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

}

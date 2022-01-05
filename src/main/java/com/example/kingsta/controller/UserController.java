package com.example.kingsta.controller;


import com.example.kingsta.config.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    @GetMapping("user/{userId}")
    public String profile(@PathVariable int userId, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        model.addAttribute("dto",principalDetails);
        return "user/profile";
    }

    @GetMapping("user/{id}/update")
    public String update(@PathVariable int id,Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
        model.addAttribute("principal",principalDetails);
        return "user/update";
    }
}

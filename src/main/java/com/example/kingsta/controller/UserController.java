package com.example.kingsta.controller;


import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.service.UserService;
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

    private final UserService userService;

    //유저 프로필
    @GetMapping("user/{userId}")
    public String profile(@PathVariable Long userId, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.profile(userId);
        model.addAttribute("dto",principalDetails);
//        model.addAttribute("images",principalDetails.getUser().getImages());
        return "user/profile";
    }

    // 유저 정보 업데이트
    @GetMapping("user/{id}/update")
    public String update(@PathVariable int id,Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
        model.addAttribute("principal",principalDetails);
        return "user/update";
    }
}

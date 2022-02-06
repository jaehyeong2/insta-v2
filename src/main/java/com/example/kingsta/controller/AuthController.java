package com.example.kingsta.controller;

import com.example.kingsta.domain.user.User;
import com.example.kingsta.dto.user.SignUpDto;
import com.example.kingsta.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Slf4j
@Controller
public class AuthController {

    private final UserService userService;

    @GetMapping("auth/signin")
    public String signInForm() {
        return "auth/signin";
    }

    @GetMapping("auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("auth/signup")
    public String signup(@Validated SignUpDto signUpDto, BindingResult bindingResult) {
            User userEntity = signUpDto.toEntity(signUpDto);
            userService.join(userEntity);
            log.info("{}님 회원가입 완료", userEntity.getUsername());
            return "redirect:/";
    }
}

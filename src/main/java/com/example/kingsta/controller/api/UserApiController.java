package com.example.kingsta.controller.api;

import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.domain.user.User;
import com.example.kingsta.dto.CommonResDto;
import com.example.kingsta.dto.UserUpdateDto;
import com.example.kingsta.handler.ex.CustomValidationApiException;
import com.example.kingsta.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("api/user/{userId}")
    public CommonResDto<?> update(@PathVariable Long userId,
                               @Validated UserUpdateDto userUpdateDto,
                               BindingResult bindingResult,
                               @AuthenticationPrincipal PrincipalDetails principalDetails){

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패",errorMap);
        }else{
            User userEntity = userService.modify(userId,userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);
            return new CommonResDto<>(1,"회원수정완료",userEntity);
        }
    }
}

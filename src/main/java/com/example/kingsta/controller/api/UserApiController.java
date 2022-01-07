package com.example.kingsta.controller.api;

import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.domain.user.User;
import com.example.kingsta.dto.CommonResDto;
import com.example.kingsta.dto.subscribe.SubscribeResDto;
import com.example.kingsta.dto.user.UserUpdateDto;
import com.example.kingsta.handler.ex.CustomValidationApiException;
import com.example.kingsta.service.SubscribeService;
import com.example.kingsta.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    //회원정보 수정
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
            log.info("{}님이 회원정보를 수정하였습니다",principalDetails.getUsername());
            return new CommonResDto<>(1,"회원수정완료",userEntity);
        }
    }

    // 회원 구독정보 불러오기
    @GetMapping("api/user/{userId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable Long userId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<SubscribeResDto> subscribeResDtoList = subscribeService.subscribeList(userId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CommonResDto<>(1,"구독정보리스트 가져옴",subscribeResDtoList), HttpStatus.OK);
    }
}

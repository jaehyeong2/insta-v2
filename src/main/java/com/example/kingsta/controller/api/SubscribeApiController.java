package com.example.kingsta.controller.api;


import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.dto.CommonResDto;
import com.example.kingsta.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("api/subscribe/{toUserId}")
    public ResponseEntity subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId){
        subscribeService.subscribe(principalDetails.getUser().getId(),toUserId);
        log.info("{}님 구독 이벤트 발생",principalDetails.getUsername());
        return new ResponseEntity<>(new CommonResDto<>(1,"구독완료",null), HttpStatus.OK);
    }

    @DeleteMapping("api/subscribe/{toUserId}")
    public ResponseEntity unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId){
        subscribeService.unSubscribe(principalDetails.getUser().getId(),toUserId);
        log.info("{}님 구독 취소 이벤트 발생",principalDetails.getUsername());
        return new ResponseEntity<>(new CommonResDto<>(1,"구독취소",null), HttpStatus.OK);
    }
}

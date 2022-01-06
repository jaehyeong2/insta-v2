package com.example.kingsta.controller.api;


import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.dto.CommonResDto;
import com.example.kingsta.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("api/subscribe/userId")
    public ResponseEntity subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId){
        subscribeService.subscribe(principalDetails.getUser().getId(),toUserId);
        return new ResponseEntity<>(new CommonResDto<>(1,"구독완료",null), HttpStatus.OK);
    }
}

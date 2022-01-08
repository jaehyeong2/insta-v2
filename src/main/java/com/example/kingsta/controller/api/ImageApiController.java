package com.example.kingsta.controller.api;


import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.domain.image.Image;
import com.example.kingsta.dto.CommonResDto;
import com.example.kingsta.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;

    @GetMapping("api/image")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @PageableDefault(size=3) Pageable pageable){
        Page<Image> images =  imageService.story(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CommonResDto<>(1, "성공", images), HttpStatus.OK);
    }

}

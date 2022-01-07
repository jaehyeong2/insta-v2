package com.example.kingsta.controller;

import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.dto.image.ImageUploadDto;
import com.example.kingsta.handler.ex.CustomValidationException;
import com.example.kingsta.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/","image"})
    public String storyForm(){
        return "image/story";
    }

    @GetMapping("image/popular")
    public String popularForm(){
        return "image/popular";
    }

    @GetMapping("image/upload")
    public String uploadForm(){
        return "image/upload";
    }

    @PostMapping("image/upload")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지가 첨부되지않았습니다",null);
        }

        imageService.upload(imageUploadDto,principalDetails);
        log.info("{}님이 이미지를 업로드하였습니다.",principalDetails.getUser().getUsername());
        return "redirect:/user/" + principalDetails.getUser().getId();
    }
}

package com.example.kingsta.controller.api;


import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.domain.image.Image;
import com.example.kingsta.dto.CommonResDto;
import com.example.kingsta.service.ImageService;
import com.example.kingsta.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;

    @GetMapping("api/image")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @PageableDefault(size=3) Pageable pageable){
        Page<Image> images =  imageService.story(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CommonResDto<>(1, "성공", images), HttpStatus.OK);
    }

    // 좋아요, 취소
    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(@PathVariable Long imageId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        likesService.like(imageId, principalDetails.getUser().getId());
        log.info("좋아요 이벤트 발생");
        return new ResponseEntity<>(new CommonResDto<>(1, "좋아요성공", null), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable Long imageId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        likesService.unLike(imageId, principalDetails.getUser().getId());
        log.info("좋아요 취소 발생");
        return new ResponseEntity<>(new CommonResDto<>(1, "좋아요취소성공", null), HttpStatus.OK);
    }

}

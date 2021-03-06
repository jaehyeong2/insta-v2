package com.example.kingsta.controller.api;

import com.example.kingsta.config.security.PrincipalDetails;
import com.example.kingsta.domain.comment.Comment;
import com.example.kingsta.dto.CommonResDto;
import com.example.kingsta.dto.comment.CommentReqDto;
import com.example.kingsta.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("api/comment")
    public ResponseEntity<?> commentSave(@Validated @RequestBody CommentReqDto commentReqDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Comment comment = commentService.writeComment(commentReqDto.getContent(), principalDetails.getUser().getId(), commentReqDto.getImageId());
        log.info("회원 {} : 댓글 작성", principalDetails.getUsername());
        return new ResponseEntity<>(new CommonResDto<>(1, "댓글 생성 성공", comment), HttpStatus.CREATED);
    }

    @DeleteMapping("api/comment/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commentService.deleteComment(id);
        log.info("{}번 댓글 삭제", id);
        return new ResponseEntity<>(new CommonResDto<>(1, "댓글삭제 성공", null), HttpStatus.OK);
    }
}

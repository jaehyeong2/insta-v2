package com.example.kingsta.dto.comment;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentReqDto {
    @NotBlank // 빈값이거나 null 체크 그리고 빈 공백까지
    private String content;
    @NotNull // null 체크
    private Long imageId;
}

package com.example.kingsta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

//@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResDto<T> {

    private int statusCode; // 1 성공 , -1 실패
    private String message;
    private T data;
}

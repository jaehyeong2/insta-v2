package com.example.kingsta.handler;


import com.example.kingsta.dto.CommonResDto;
import com.example.kingsta.handler.ex.CustomValidationException;
import com.example.kingsta.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationExceptionHandler(CustomValidationException e){
//        if(e.getErrorMap()==null) {
//            return Script.back(e.getMessage());
//        }else{
            return Script.back(e.getErrorMap().toString());
//        }
    }
}

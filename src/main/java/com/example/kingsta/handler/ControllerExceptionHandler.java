package com.example.kingsta.handler;


import com.example.kingsta.dto.CommonResDto;
import com.example.kingsta.handler.ex.CustomApiException;
import com.example.kingsta.handler.ex.CustomValidationApiException;
import com.example.kingsta.handler.ex.CustomValidationException;
import com.example.kingsta.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

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

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiExceptionHandler(CustomValidationApiException e){
        return new ResponseEntity<>(new CommonResDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> ApiExceptionHandler(CustomApiException e){
        return new ResponseEntity<>(new CommonResDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
    }
}

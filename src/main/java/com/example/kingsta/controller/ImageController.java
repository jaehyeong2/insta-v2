package com.example.kingsta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    @GetMapping({"/","image"})
    public String story(){
        return "image/story";
    }

    @GetMapping("image/popular")
    public String popular(){
        return "image/popular";
    }

    @GetMapping("image/upload")
    public String upload(){
        return "image/upload";
    }
}

package com.example.kingsta.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class SignUoDto {

    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    @Range(min = 2, max = 20)
    private String password;

    @NotNull
    private String email;
}

package com.example.kingsta.dto.user;

import com.example.kingsta.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpDto {

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    public User toEntity(SignUpDto signUoDto) {
        return User.builder().name(name).username(username).email(email).password(password)
                .build();
    }
}

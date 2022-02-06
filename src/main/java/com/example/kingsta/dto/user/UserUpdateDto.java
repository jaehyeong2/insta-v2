package com.example.kingsta.dto.user;

import com.example.kingsta.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;

    public User toEntity() {
        return User.builder()
                .name(name).username(username).password(password).email(email)
                .build();
    }
}

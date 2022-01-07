package com.example.kingsta.dto.user;

import com.example.kingsta.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {

    private User user;

    private int subscribeCount;
    private int imageCount;

    private boolean subscribeState;
    private boolean pageOwnerState;
}

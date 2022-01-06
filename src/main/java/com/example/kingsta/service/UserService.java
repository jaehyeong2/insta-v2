package com.example.kingsta.service;

import com.example.kingsta.domain.user.User;
import com.example.kingsta.domain.user.UserRepository;
import com.example.kingsta.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    //회원가입
    public User join(User user) {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);

        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        User userEntity = userRepository.save(user);

        return userEntity;
    }

    //유저 정보 수정
    public User modify(Long id,User user){
        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("찾을 수 없는 ID입니다");
        });

        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encodedPassword = encoder.encode(rawPassword);
        userEntity.setPassword(encodedPassword);

        return userEntity;
    }

    // 유저 프로필
    @Transactional(readOnly = true)
    public User profile(Long userId){
        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지가 존재하지 않습니다");
        });

        return userEntity;
    }
}

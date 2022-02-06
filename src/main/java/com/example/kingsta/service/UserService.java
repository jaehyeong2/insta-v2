package com.example.kingsta.service;

import com.example.kingsta.domain.subscribe.SubscribeRepository;
import com.example.kingsta.domain.user.User;
import com.example.kingsta.domain.user.UserRepository;
import com.example.kingsta.dto.user.UserProfileDto;
import com.example.kingsta.handler.ex.CustomApiException;
import com.example.kingsta.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private static String uploadFolder = "C:/Users/wogud2/upload/";

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
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
    public User modify(Long id, User user) {
        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("찾을 수 없는 ID입니다");
        });

        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encodedPassword = encoder.encode(rawPassword);
        userEntity.setPassword(encodedPassword);

        return userEntity;
    }

    // 유저 프로필 읽어오기
    @Transactional(readOnly = true)
    public UserProfileDto profile(Long userId, Long principalId) {
        UserProfileDto userProfileDto = new UserProfileDto();

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지가 존재하지 않습니다");
        });

        // 좋아요 갯수 리턴
        userEntity.getImages().forEach((image) -> {
            image.setLikeCount(image.getLikes().size());
        });

        // 구독 개수, 상태
        int subscribeCount = subscribeRepository.mSubscribeCount(userId);
        int subscribeState = subscribeRepository.mSubscribeState(principalId, userId);

        userProfileDto.setUser(userEntity);
        userProfileDto.setPageOwnerState(userId == principalId); // 1이면 본인 프로필 정보
        userProfileDto.setImageCount(userEntity.getImages().size());

        userProfileDto.setSubscribeCount(subscribeCount);
        userProfileDto.setSubscribeState(subscribeState == 1);

        return userProfileDto;
    }

    //유저 프로필 사진 변경
    public User profileImageUpdate(Long principalId, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename(); // 1.jpg
        System.out.println("이미지 파일이름 : " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        User userEntity = userRepository.findById(principalId).orElseThrow(() -> {
            // throw -> return 으로 변경
            return new CustomApiException("유저를 찾을 수 없습니다.");
        });
        userEntity.setProfileImageUrl(imageFileName);

        return userEntity;
    } // 더티체킹으로 업데이트 됨.
}

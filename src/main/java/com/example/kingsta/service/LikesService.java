package com.example.kingsta.service;

import com.example.kingsta.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    public void like(Long imageId, Long principalId) {
        likesRepository.mLikes(imageId, principalId);
    }

    public void unLike(Long imageId, Long principalId) {
        likesRepository.mUnLikes(imageId, principalId);
    }
}

package com.example.kingsta.service;

import com.example.kingsta.domain.subscribe.SubscribeRepository;
import com.example.kingsta.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    //구독
    public void subscribe(Long fromUserId, Long toUserId){
        try {
            subscribeRepository.mSubscribe(fromUserId,toUserId);
        } catch (Exception e){
            throw new CustomApiException("이미 구독중입니다");
        }
    }
    
    //구독 취소
    public void unSubscribe(Long fromUserId, Long toUserId){
            subscribeRepository.mUnSubscribe(fromUserId,toUserId);
    }
}

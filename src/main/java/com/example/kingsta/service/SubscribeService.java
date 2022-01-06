package com.example.kingsta.service;

import com.example.kingsta.domain.subscribe.SubscribeRepository;
import com.example.kingsta.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    public void subscribe(Long fromUserId, Long toUserId){
        try {
            subscribeRepository.mSubscribe(fromUserId,toUserId);
        } catch (Exception e){
            throw new CustomApiException("이미 구독중입니다");
        }
    }
}

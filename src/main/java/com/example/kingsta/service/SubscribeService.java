package com.example.kingsta.service;

import com.example.kingsta.domain.subscribe.SubscribeRepository;
import com.example.kingsta.dto.subscribe.SubscribeResDto;
import com.example.kingsta.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    //구독
    public void subscribe(Long fromUserId, Long toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독중입니다");
        }
    }

    //구독 취소
    public void unSubscribe(Long fromUserId, Long toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }

    // 구독 정보 불러오기
    @Transactional(readOnly = true)
    public List<SubscribeResDto> subscribeList(Long userId, Long principalId) {

        // 쿼리 준비
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT u.user_id, u.username, u.profileImageUrl, ");
        sb.append("if ((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.user_id), 1, 0) subscribeState, ");
        sb.append("if ((?=u.user_id), 1, 0) equalUserState ");
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.user_id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ?");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, userId);

        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeResDto> subscribeDtos = result.list(query, SubscribeResDto.class);

        return subscribeDtos;
    }
}

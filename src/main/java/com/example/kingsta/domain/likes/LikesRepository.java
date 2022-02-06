package com.example.kingsta.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "INSERT INTO likes(image_id, user_id, createDate) VALUES(:image_id, :principalId, now())", nativeQuery = true)
    int mLikes(Long image_id, Long principalId);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE image_id = :image_id AND user_id = :principalId", nativeQuery = true)
    int mUnLikes(Long image_id, Long principalId);
}

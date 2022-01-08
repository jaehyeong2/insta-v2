package com.example.kingsta.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value = "SELECT * FROM image WHERE user_id IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalId) ORDER BY image_id DESC", nativeQuery = true)
    Page<Image> mStory(Long principalId, Pageable pageable);

    @Query(value = "SELECT i.* FROM image i INNER JOIN (SELECT image_id, COUNT(image_id) likeCount FROM likes GROUP BY image_id) c ON i.id = c.imageId ORDER BY likeCount DESC", nativeQuery = true)
    List<Image> mPopular();
}

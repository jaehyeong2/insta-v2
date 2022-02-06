package com.example.kingsta.domain.comment;

import com.example.kingsta.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

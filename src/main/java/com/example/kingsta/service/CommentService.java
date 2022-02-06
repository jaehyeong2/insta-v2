package com.example.kingsta.service;

import com.example.kingsta.domain.comment.Comment;
import com.example.kingsta.domain.comment.CommentRepository;
import com.example.kingsta.domain.image.Image;
import com.example.kingsta.domain.user.User;
import com.example.kingsta.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Comment writeComment(String content, Long userId, Long imageId) {

        Image image = new Image();
        image.setId(imageId);

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new IllegalArgumentException("유저 아이디를 찾을 수 없습니다.");
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setImage(image);
        comment.setUser(userEntity); // 이미지랑 유저 만들어서 id값만 담고 코멘트에 set

        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

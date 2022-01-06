package com.example.kingsta.domain.image;

import com.example.kingsta.domain.comment.Comment;
import com.example.kingsta.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    private String caption;
    private String postImageUrl;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "image")
    private List<Comment> commentList;

    private LocalDateTime createDate;
    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}

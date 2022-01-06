package com.example.kingsta.domain.user;

import com.example.kingsta.domain.comment.Comment;
import com.example.kingsta.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Size(max = 20, min = 2)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String profileImageUrl;
    private String role;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Comment> comments;

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}

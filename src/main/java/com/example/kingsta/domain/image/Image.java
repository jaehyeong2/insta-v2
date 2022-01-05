package com.example.kingsta.domain.image;

import com.example.kingsta.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @OneToMany(mappedBy = "image")
    private List<Comment> commentList;
}

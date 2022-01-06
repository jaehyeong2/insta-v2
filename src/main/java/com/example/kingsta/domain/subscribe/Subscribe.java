package com.example.kingsta.domain.subscribe;

import com.example.kingsta.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_uk",
                        columnNames = {"from_user_id","to_user_id"})}
)

public class Subscribe {

    @Id
    @GeneratedValue
    @Column(name = "subscribe_id")
    private Long id;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private User toUser;

    private LocalDateTime createDate;

    @PrePersist
    public void  createDate() {
        this.createDate = LocalDateTime.now();
    }
}

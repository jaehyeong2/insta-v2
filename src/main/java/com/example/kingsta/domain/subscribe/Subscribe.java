package com.example.kingsta.domain.subscribe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Subscribe {

    @Id
    @GeneratedValue
    @Column(name = "subscribe_id")
    private Long id;
}

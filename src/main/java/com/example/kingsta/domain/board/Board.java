package com.example.kingsta.domain.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Board {

    @Id
    @GeneratedValue
    private Long id;
}

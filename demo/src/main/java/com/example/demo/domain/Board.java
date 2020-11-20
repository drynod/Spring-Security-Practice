package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Board {
    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    private String title;
    private String author;
    private LocalDateTime localDateTime;
    private String content;
    private String password;
}

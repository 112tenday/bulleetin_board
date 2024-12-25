package com.iwan.bulletinboard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String password;
    private String content;
    private int views = 0;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;


    private String formattedCreated_at;
    private String formattedUpdated_at;
}

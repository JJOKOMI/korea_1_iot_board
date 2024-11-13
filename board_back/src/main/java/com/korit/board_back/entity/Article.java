package com.korit.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, name = "author_id")
    private Long authorId;

    @Builder.Default // 게시글이 담길 장소를 만들겠다. 알티클이 객체화될때 댓글이 담길 리스트구조를 기본적으로 만든다.
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true) //커멘트에있는 알티클과 연결
    private List<Comment> comments = new ArrayList<>();
}
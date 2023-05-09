package com.jwt.study.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue
    private Long id;
    private String name;
    private Long parentId;
    @ManyToMany
    @JoinTable(name = "category_board",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id"))
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Category(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}

package com.jwt.study.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

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

    @Builder
    public Category(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}

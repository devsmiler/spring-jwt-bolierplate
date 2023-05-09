package com.jwt.study.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Category implements Serializable {
    @Id
    @Column(name = "category_id")
    @GeneratedValue
    private Long id;
    private String name;
    private Long parentId;
}

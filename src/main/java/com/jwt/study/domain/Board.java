package com.jwt.study.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
@Builder @AllArgsConstructor
public class Board extends BaseTimeEntity {
    @Id @Column(name = "board_id")
    @Getter
    @GeneratedValue
    private Long id;
    private String contents;
    private String writer;
    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    public void setMember(Member member){
        this.member = member;
        member.getBoards().add(this);
    }
}

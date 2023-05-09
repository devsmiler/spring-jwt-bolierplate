package com.jwt.study.domain;

import com.jwt.study.dto.request.UpdateBoard;
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
    @GeneratedValue
    private Long id;
    private String contents;
    private String writer;
    @Column(nullable = false)
    private Integer commentCount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;
    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void edit(UpdateBoard updateBoard) {

        if (updateBoard.getContents() != null){
            contents = updateBoard.getContents();
        }
    }
    public void setMember(Member member){
        this.member = member;
        member.getBoards().add(this);
    }
    public void addCommentCount() {
        commentCount += 1;
    }
    public void minusCommentCount() {
        if (commentCount > 0) {
            commentCount -=1 ;
        }
    }
    public void delete() {
        boardStatus = BoardStatus.deleted;
    }
}

package com.jwt.study.domain;

import com.jwt.study.dto.request.UpdateComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder @Getter
public class Comment extends BaseTimeEntity{
    @Id @GeneratedValue @Column(name = "comment_id")
    private Long id;
    private String contents;
    private String writer;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    public void setMember(Member member){
        this.member = member;
        member.getComments().add(this);
    }
    public void setBoard(Board board){
        this.board = board;
        board.getComments().add(this);
    }
    public void delete() {
        this.commentStatus = CommentStatus.deleted;
    }

    public void edit(UpdateComment updateComment) {
        if (updateComment.getContents() != null) {
            contents = updateComment.getContents();
            //member writer  업데이트 추가 남아있음
        }
    }
}

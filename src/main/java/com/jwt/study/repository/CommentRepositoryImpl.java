package com.jwt.study.repository;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.BoardStatus;
import com.jwt.study.domain.Comment;
import com.jwt.study.domain.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.jwt.study.domain.QBoard.board;
import static com.jwt.study.domain.QComment.comment;


@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Comment> getCommentsByBoardId(Board board) {
        return jpaQueryFactory
                .select(comment)
                .from(comment)
                .where(comment.board.eq(board))
                .orderBy(comment.id.desc()).fetch();
    }
}

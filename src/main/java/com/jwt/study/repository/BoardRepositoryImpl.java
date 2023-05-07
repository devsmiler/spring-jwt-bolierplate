package com.jwt.study.repository;

import com.jwt.study.domain.Board;

import com.jwt.study.domain.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.jwt.study.domain.QBoard.board;


@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Board> getList() {
        return jpaQueryFactory
                .selectFrom(board)
                .orderBy(board.id.desc())
                .fetch();
    }
}

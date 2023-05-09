package com.jwt.study.repository;

import com.jwt.study.domain.BoardStatus;
import com.jwt.study.dto.response.BoardResponse;
import com.jwt.study.dto.response.QBoardResponse;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.jwt.study.domain.QBoard.board;


@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<BoardResponse> getList(Pageable pageable) {
        List<BoardResponse> boards = jpaQueryFactory
                .select(new QBoardResponse(
                        board
                ))
                .from(board)
                .where(board.boardStatus.eq(BoardStatus.active))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.id.desc())
                .fetch();

        JPAQuery<Long> total = jpaQueryFactory
                .select(board.count())
                .from(board);

        return PageableExecutionUtils.getPage(boards, pageable, total::fetchOne);

    }

}

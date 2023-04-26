package com.jwt.study.service;

import com.jwt.study.annotation.Facade;
import com.jwt.study.domain.Board;
import com.jwt.study.domain.Comment;
import com.jwt.study.domain.Member;
import com.jwt.study.dto.request.CreateComment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
public class BoardFacade {
    private final BoardService boardService;
    private final CommentService commentService;
    private final MemberDetailService memberDetailService;
    @Transactional
    public Long createComment (
            Long boardId,
            CreateComment createComment,
            Authentication authentication) {
        Member member = memberDetailService.getMemberIdFromAuth(authentication);
        Board board = boardService.getBoardById(boardId);

        createComment.setWriter(member.getEmail());

        return commentService.create(board, createComment, member);
    }
}

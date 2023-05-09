package com.jwt.study.service;

import com.jwt.study.annotation.Facade;
import com.jwt.study.domain.Board;
import com.jwt.study.domain.Comment;
import com.jwt.study.domain.Member;
import com.jwt.study.dto.request.CreateBoard;
import com.jwt.study.dto.request.CreateComment;
import com.jwt.study.dto.request.UpdateBoard;
import com.jwt.study.dto.request.UpdateComment;
import com.jwt.study.dto.response.BoardComment;
import com.jwt.study.dto.response.BoardResponse;
import com.jwt.study.dto.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Facade
@RequiredArgsConstructor @Slf4j
public class BoardFacade {
    private final BoardService boardService;
    private final CommentService commentService;
    private final MemberDetailService memberDetailService;

    @Transactional
    public Long createBoard (
            Authentication authentication,
            CreateBoard createBoard
    ) {
        Member member = memberDetailService.getMemberIdFromAuth(authentication);
        return boardService.createBoard(createBoard, member);
    }
    @Transactional(readOnly = true)
    public BoardComment getBoardOne (
            Long boardId,
            Authentication authentication
    ) {
        Member member = memberDetailService.getMemberIdFromAuth(authentication);
        Board board = boardService.getBoardById(boardId);
        List<Comment> comments = commentService.getCommentsBoardById(board);
        return new BoardComment(
                new BoardResponse(board),
                comments.stream()
                        .map(CommentResponse::new)
                        .collect(Collectors.toList())
                );
    }

    @Transactional
    public void updateBoard (
            Long boardId,
            Authentication authentication,
            UpdateBoard updateBoard
    ) {
        Member member = memberDetailService.getMemberIdFromAuth(authentication);
        boardService.updateBoard(boardId, member, updateBoard);
    }

    public void deleteBoard(Long boardId,
                            Authentication authentication) {
        Member member = memberDetailService.getMemberIdFromAuth(authentication);
        boardService.deleteBoard(boardId, member);
    }
    @Transactional
    public Long createComment (
            Long boardId,
            CreateComment createComment,
            Authentication authentication) {
        Member member = memberDetailService.getMemberIdFromAuth(authentication);
        log.info(member.toString());
        Board board = boardService.addCommentCount(boardId);
        log.info(board.toString());
        createComment.setWriter(member.getEmail());

        return commentService.create(board, createComment, member);
    }

    @Transactional
    public void deleteComment(Long boardId,
                            Long commentId,
                            Authentication authentication) {
        Member member = memberDetailService.getMemberIdFromAuth(authentication);
        boardService.minusCommentCount(boardId);
        commentService.delete(commentId, member);
    }
    @Transactional
    public void updateComment(Long boardId,
                              Long commentId,
                              UpdateComment updateComment,
                              Authentication authentication) {
        Member member = memberDetailService.getMemberIdFromAuth(authentication);
        commentService.update(commentId, member, updateComment);
    }
}

package com.jwt.study.service;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.Comment;
import com.jwt.study.domain.Member;
import com.jwt.study.dto.request.CreateComment;
import com.jwt.study.dto.request.UpdateComment;
import com.jwt.study.dto.response.CommentResponse;
import com.jwt.study.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor @Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    public Long create (
            Board board,
            CreateComment createComment,
            Member member ) {
        Comment comment = createComment.toEntity();
        comment.setMember(member);
        comment.setBoard(board);

        Comment save = commentRepository.save(comment);
        return save.getId();
    }
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
    }
    public List<Comment> getCommentsBoardById(Board board) {
        return commentRepository.getCommentsByBoardId(board);
    }
    public void delete(Long commentId, Member member) {
        Comment comment = getCommentById(commentId);
        if (comment.getMember().equals(member)) {
            comment.delete();
        } else {
            log.info("unauthorized"); // 예외 처리 필수
        }
    }
    public void update(Long commentId, Member member, UpdateComment updateComment) {
        Comment comment = getCommentById(commentId);
        if (comment.getMember().equals(member)) {
            comment.edit(updateComment);
        } else {
            log.info("unauthorized"); // 예외 처리 필수
        }
    }
}

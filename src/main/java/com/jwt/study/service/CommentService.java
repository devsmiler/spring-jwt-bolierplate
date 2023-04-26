package com.jwt.study.service;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.Comment;
import com.jwt.study.domain.Member;
import com.jwt.study.dto.request.CreateComment;
import com.jwt.study.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
}

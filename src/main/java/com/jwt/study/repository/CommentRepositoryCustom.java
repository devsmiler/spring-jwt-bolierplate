package com.jwt.study.repository;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> getCommentsByBoardId(Board board);
}

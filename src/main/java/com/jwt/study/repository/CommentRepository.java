package com.jwt.study.repository;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

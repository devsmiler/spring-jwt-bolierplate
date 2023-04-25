package com.jwt.study.service;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.Comment;
import com.jwt.study.domain.Member;
import com.jwt.study.dto.request.CreateBoard;
import com.jwt.study.dto.request.CreateComment;
import com.jwt.study.repository.BoardRepository;
import com.jwt.study.repository.CommentRepository;
import com.jwt.study.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service @RequiredArgsConstructor @Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    @Transactional
    public Long createBoard(CreateBoard createBoard, Authentication authentication){
        log.info("auth get name :"+ (Member) authentication.getPrincipal());
        Member m = (Member) authentication.getPrincipal();
        Member member = memberRepository.findById(m.getId()).orElseThrow(RuntimeException::new);

        createBoard.setWriter(member.getEmail());
        Board board = createBoard.toEntity();
        board.setMember(member);
        Board save = boardRepository.save(board);
        return 1L;
    }
    public void getBoards(){

    }
    public void getOneBoard(){

    }
    public void updateBoard(){

    }

    public Long createComment(
            Long boardId,
            CreateComment createComment,
            Authentication authentication ) {
        Member m = (Member) authentication.getPrincipal();
        Member member = memberRepository.findById(m.getId()).orElseThrow(RuntimeException::new);

        Board board = boardRepository.findById(boardId).orElseThrow(RuntimeException::new);

        createComment.setWriter(member.getEmail());
        Comment comment = createComment.toEntity();
        comment.setMember(member);
        comment.setBoard(board);

        Comment save = commentRepository.save(comment);
        return save.getId();
    }
}

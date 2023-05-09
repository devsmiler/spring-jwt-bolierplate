package com.jwt.study.service;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.Member;
import com.jwt.study.dto.request.CreateBoard;
import com.jwt.study.dto.response.BoardResponse;
import com.jwt.study.dto.request.UpdateBoard;
import com.jwt.study.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long createBoard(CreateBoard createBoard, Member member){
        createBoard.setWriter(member.getEmail());
        Board board = createBoard.toEntity();
        board.setMember(member);
        Board save = boardRepository.save(board);
        return save.getId();
    }
    public Page<BoardResponse> getBoards(Pageable pageable){
        return boardRepository.getList(pageable);
    }
    public BoardResponse getBoardOne(Long boardId){
        Board board = getBoardById(boardId);
        return new BoardResponse(board);
    }
    public Board getBoardById(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(RuntimeException::new);
    }
    @Transactional
    public Board addCommentCount(Long boardId) {
        Board board = this.getBoardById(boardId);
        board.addCommentCount();
        return board;
    }
    @Transactional
    public void minusCommentCount(Long boardId) {
        Board board = this.getBoardById(boardId);
        board.minusCommentCount();
    }
    @Transactional
    public void updateBoard(Long boardId, Member member, UpdateBoard updateBoard){
        Board board = this.getBoardById(boardId);
        if (board.getMember().equals(member)){
            board.edit(updateBoard);
        } else {
            log.info("unauthorized"); // 예외 처리 필수
        }
    }
    @Transactional
    public void deleteBoard(Long boardId, Member member){
        Board board = this.getBoardById(boardId);
        if (board.getMember().equals(member)){
            board.delete();
        } else {
            log.info("unauthorized"); // 예외 처리 필수
        }
    }
}

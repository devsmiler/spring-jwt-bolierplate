package com.jwt.study.service;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.Member;
import com.jwt.study.dto.request.CreateBoard;
import com.jwt.study.dto.response.BoardResponse;
import com.jwt.study.dto.request.UpdateBoardDto;
import com.jwt.study.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<BoardResponse> getBoards(){
            return boardRepository.getList()
                    .stream()
                    .map(BoardResponse::new)
                    .collect(Collectors.toList());
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
    public Board minusCommentCount(Long boardId) {
        Board board = this.getBoardById(boardId);
        board.minusCommentCount();
        return board;
    }
    @Transactional
    public void updateBoard(Long boardId, Member member, UpdateBoardDto updateBoardDto){
        Board board = this.getBoardById(boardId);
        if (board.getMember().equals(member)){
            log.info(updateBoardDto.toString());
            log.info("im users");
            board.edit(updateBoardDto);
        } else {
            log.info("unauthorized"); // 예외 처리 필수
        }
    }
    @Transactional
    public void deleteBoard(Long boardId, Member member){
        Board board = this.getBoardById(boardId);
        if (board.getMember().equals(member)){
            log.info("im users");
            board.delete();
        } else {
            log.info("unauthorized"); // 예외 처리 필수
        }
    }
}

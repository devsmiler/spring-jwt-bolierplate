package com.jwt.study.service;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.Member;
import com.jwt.study.dto.request.CreateBoard;
import com.jwt.study.dto.response.BoardResponse;
import com.jwt.study.dto.request.UpdateBoard;
import com.jwt.study.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    /*
    @Cacheable(cacheNames = ..., key = ...)
    : 해당 메소드의 리턴값을 캐시에 저장. 메소드가 호출될 때마다 캐시 저장소에
    저장된 캐시가 있는지 확인하고 있다면 메소드를 실행하지 않고 캐시를 반환.
    cacheNames - 캐시 이름
    key - 캐시에 접근하기 위한 키 값
    * Redis에 실제로 저장될 때는 "cacheNames::key" 형식으로 저장된다.

    @CacheEvict(cacheNames = ..., key = ..., allEntries = ...)
    : 지정된 키에 해당하는 캐시를 삭제
    allEntries - true일 경우, 같은 이름을 가진 모든 캐시를 삭제

    @Caching(evict = ..., put = ...)
    : 여러 캐싱 작업을 한 번에 적용시키기 위한 어노테이션
     */
    @Transactional
    public Long createBoard(CreateBoard createBoard, Member member){
        createBoard.setWriter(member.getEmail());
        Board board = createBoard.toEntity();
        board.setMember(member);
        Board save = boardRepository.save(board);
        return save.getId();
    }
    @Cacheable(key = "#pageable", value = "getBoards")
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

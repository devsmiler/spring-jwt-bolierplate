package com.jwt.study.controller;

import com.jwt.study.dto.request.CreateBoard;
import com.jwt.study.dto.request.CreateComment;
import com.jwt.study.dto.request.UpdateBoard;
import com.jwt.study.dto.request.UpdateComment;
import com.jwt.study.dto.response.BoardComment;
import com.jwt.study.dto.response.BoardResponse;
import com.jwt.study.service.BoardFacade;
import com.jwt.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardFacade boardFacade;
    private final BoardService boardService;
    @PreAuthorize("hasAnyAuthority('GUEST')")
    @PostMapping("/board")
    public Long createBoard(
            @RequestBody CreateBoard createBoard,
            Authentication authentication
    ) {
        return boardFacade.createBoard (
                authentication,
                createBoard
        );
    }
    @PreAuthorize("hasAnyAuthority('GUEST')")
    @PutMapping("/board/{boardId}")
    public void updateBoard(
            @PathVariable Long boardId,
            @RequestBody UpdateBoard updateBoard,
            Authentication authentication
    ) {
        boardFacade.updateBoard (
                boardId,
                authentication,
                updateBoard
        );
    }
    @PreAuthorize("hasAnyAuthority('GUEST')")
    @DeleteMapping("/board/{boardId}")
    public void deleteBoard(
            @PathVariable Long boardId,
            Authentication authentication
    ) {
        boardFacade.deleteBoard (
                boardId,
                authentication
        );
    }
    @PreAuthorize("hasAnyAuthority('GUEST')")
    @GetMapping("/board")
    public Page<BoardResponse> getBoards(
            Pageable pageable,
            Authentication authentication
    ) {
        return boardService.getBoards(pageable);
    }
    @PreAuthorize("hasAnyAuthority('GUEST')")
    @GetMapping("/board/{boardId}")
    public BoardComment getBoardOne(
            @PathVariable Long boardId,
            Authentication authentication
    ) {
        return boardFacade.getBoardOne (
                boardId,
                authentication
        );
    }

    @PreAuthorize("hasAnyAuthority('GUEST')")
    @PostMapping("/board/{boardId}")
    public Long createComment(
            @PathVariable Long boardId,
            @RequestBody CreateComment createComment,
            Authentication authentication
    ) {
        return boardFacade.createComment (
                boardId,
                createComment,
                authentication
        );
    }
    @PreAuthorize("hasAnyAuthority('GUEST')")
    @DeleteMapping("/board/{boardId}/comment/{commentId}")
    public void deleteComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            Authentication authentication
    ) {
        boardFacade.deleteComment (
                boardId,
                commentId,
                authentication
        );
    }

    @PreAuthorize("hasAnyAuthority('GUEST')")
    @PutMapping("/board/{boardId}/comment/{commentId}")
    public void updateComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @RequestBody UpdateComment updateComment,
            Authentication authentication
    ) {
        boardFacade.updateComment (
                boardId,
                commentId,
                updateComment,
                authentication
        );
    }
}

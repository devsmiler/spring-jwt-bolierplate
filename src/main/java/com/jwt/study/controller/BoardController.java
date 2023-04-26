package com.jwt.study.controller;

import com.jwt.study.dto.request.CreateBoard;
import com.jwt.study.dto.request.CreateComment;
import com.jwt.study.service.BoardFacade;
import com.jwt.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardFacade boardFacade;
    @PreAuthorize("hasAnyAuthority('GUEST')")
    @PostMapping("/board")
    public Long createBoard(
            @RequestBody CreateBoard createBoard,
            Authentication authentication
            ) {
        return boardService.createBoard(
                createBoard,
                authentication);
    }

    @PreAuthorize("hasAnyAuthority('GUEST')")
    @PostMapping("/board/{boardId}")
    public Long createComment(
            @PathVariable Long boardId,
            @RequestBody CreateComment createComment,
            Authentication authentication
    ) {
        return boardFacade.createComment(
                boardId,
                createComment,
                authentication);
    }
}

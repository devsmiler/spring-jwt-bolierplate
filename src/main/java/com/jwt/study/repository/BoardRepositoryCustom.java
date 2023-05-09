package com.jwt.study.repository;

import com.jwt.study.dto.response.BoardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<BoardResponse> getList(Pageable pageable);

}

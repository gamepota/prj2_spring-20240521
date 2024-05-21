package com.prj2_spring20240521.service.board;

import com.prj2_spring20240521.domain.board.Board;
import com.prj2_spring20240521.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;

    public void add(Board board) {
        mapper.insert(board);
    }

    public boolean validate(Board board) {
        if (board.getTitle() == null || board.getTitle().isBlank()) {
            return false;
        }

        if (board.getContent() == null || board.getContent().isBlank()) {
            return false;
        }

        if (board.getWriter() == null || board.getWriter().isBlank()) {
            return false;
        }
        return true;
    }
}
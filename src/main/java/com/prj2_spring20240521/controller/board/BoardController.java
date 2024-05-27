package com.prj2_spring20240521.controller.board;

import com.prj2_spring20240521.domain.board.Board;
import com.prj2_spring20240521.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @PostMapping("add")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity add(
            Authentication authentication,
            @RequestBody Board board) {
//        Thread.sleep(10000); => 응답 지연 시키는 메소드인데 이거 말고도 몇개 더 필요함.  쓸 일 있을때 강의 영상05/21 참조
        if (service.validate(board)) {
            service.add(board, authentication);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("list")
    public Map<String, Object> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(value = "type", required = false) String searchType,
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        return service.list(page, searchType, keyword);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        Board board = service.get(id);

        if (board == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(board);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity delete(@PathVariable Integer id, Authentication authentication) {
        if (service.hasAccess(id, authentication)) {
            service.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("edit")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity edit(@RequestBody Board board,
                               Authentication authentication) {
        if (!service.hasAccess(board.getId(), authentication)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (service.validate(board)) {
            service.edit(board);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

  
}

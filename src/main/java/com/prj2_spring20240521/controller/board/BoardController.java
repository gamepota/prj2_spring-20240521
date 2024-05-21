package com.prj2_spring20240521.controller.board;

import com.prj2_spring20240521.domain.board.Board;

import com.prj2_spring20240521.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @PostMapping("add")
    public ResponseEntity add(@RequestBody Board board) {
//        Thread.sleep(10000); => 응답 지연 시키는 메소드인데 이거 말고도 몇개 더 필요함.  쓸 일 있을때 강의 영상05/21 참조
        if (service.validate(board)) {
            service.add(board);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("list")
    public List<Board> list() {
        return service.list();
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
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
    @PutMapping("edit")
    public void edit(@RequestBody Board board) {
        service.edit(board);
    }
}

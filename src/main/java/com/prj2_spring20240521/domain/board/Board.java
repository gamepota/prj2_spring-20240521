package com.prj2_spring20240521.domain.board;

import lombok.Data;

@Data
public class Board {
    private String title;
    private String content;
    private String writer;
}

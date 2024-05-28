package com.prj2_spring20240521.domain.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class Board {
    private Integer id;
    private String title;
    private String content;
    private String writer; // 작성자 nickName
    private Integer memberId;
    private LocalDateTime inserted;

    private MultipartFile[] files;
}

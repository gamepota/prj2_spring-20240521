package com.prj2_spring20240521.mapper.board;

import com.prj2_spring20240521.domain.board.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO board (title, content, writer)
            VALUES (#{title}, #{content}, #{writer})
            """)
     int insert(Board board);
}

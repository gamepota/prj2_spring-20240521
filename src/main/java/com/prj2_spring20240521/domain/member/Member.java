package com.prj2_spring20240521.domain.member;

import lombok.Data;

@Data
public class Member {
    private String email;
    private String password;
    private String nickName;
}
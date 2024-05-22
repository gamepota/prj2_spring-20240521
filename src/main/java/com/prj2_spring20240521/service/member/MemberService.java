package com.prj2_spring20240521.service.member;


import com.prj2_spring20240521.domain.member.Member;
import com.prj2_spring20240521.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberService {
    final MemberMapper mapper;

    public void add(Member member) {
        mapper.insert(member);
    }


    public Member getByEmail(String email) {
        return mapper.selectByEmail(email);
    }
}

package com.prj2_spring20240521.controller.member;

import com.prj2_spring20240521.domain.member.Member;
import com.prj2_spring20240521.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService service;

    @PostMapping("signup")
    public void signup(@RequestBody Member member) {
//        System.out.println("member = " + member);
        service.add(member);
    }

    @GetMapping(value = "check", params ="email")
    public ResponseEntity checkEmail(@RequestParam("email") String email) {
        Member member = service.getByEmail(email);
        if (member == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(email);
    }

    @GetMapping(value = "check", params ="nickName")
    public ResponseEntity checkNickName(@RequestParam("nickName") String nickName) {
        Member member =service.getByNickName(nickName);
        if(member == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(nickName);

    }
}

package com.prj2_spring20240521.controller.member;

import com.prj2_spring20240521.domain.member.Member;
import com.prj2_spring20240521.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService service;

    @PostMapping("signup")
    public ResponseEntity signup(@RequestBody Member member) {
        if (service.validate(member)) {
            service.add(member);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
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

    @GetMapping(value = "censor", params ="nickName")
    public ResponseEntity censorNickName(@RequestParam("nickName") String nickName) {
        Member member =service.checkByRule(nickName);
        if(member.equals(false)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nickName);
    }

    @GetMapping("list")
    public List<Member> list() {
        return service.list();
    }
    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        Member member =service.getById(id);
        if(member == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(member);
        }


    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        service.remove(id);
    }


}

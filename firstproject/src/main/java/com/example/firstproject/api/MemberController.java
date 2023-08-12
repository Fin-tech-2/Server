package com.example.firstproject.api;

import com.example.firstproject.dto.MemberDTO;
import com.example.firstproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;

@RestController // 이걸 사용하면 Rest Controller로 변경 -> 반환 타입이 JSON이 됨
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 출력요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute com.example.firstproject.dto.MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public ResponseEntity login(@RequestBody MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
                System.out.println("컨트롤러 : "+loginResult.getMemberName() + ", " + loginResult.getMemberEmail());
                return ResponseEntity.ok(loginResult);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디 또는 비밀번호가 일치하지 않습니다.");

//        if(loginResult != null) {
//            //login 성공
//            //System.out.println("memberDTO = " + memberDTO + ", session = " + session);
//            System.out.println("MemberController.login");
//            session.setAttribute("loginEmail", loginResult.getMemberEmail());
//            return "로그인 성공";
//        } else {
//            //login 실패시 첫 화면 으로
//            return "로그인실패";
//        }
    }
}

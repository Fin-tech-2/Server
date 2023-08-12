package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class firstController {
    //URL 요청 연결
    @GetMapping("/hi")
    //아래 메소드를 가지고 뷰템플릿 페이지를 반환할 수 있게 해줘야 한다.
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "피치");
        return "greetings"; //응답 페이지 서정 return "페이지명";
        //templates/greetings.mustache -> 브라우저로 전송해준다.
    }

    @GetMapping("/bye")
    public String seeYou(Model model) {
        model.addAttribute("nickname", "피치");
        return "goodbye";
    }
}

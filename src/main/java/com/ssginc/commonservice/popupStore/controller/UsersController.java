package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j // 로깅을 위한 Lombok 어노테이션 (로그 출력 가능)
@Controller // Spring MVC의 컨트롤러로 지정 (View 반환)
@RequestMapping("/popupStore") // 기본 URL 매핑: /popupStore
@RequiredArgsConstructor // 생성자를 자동으로 만들어줌 (final 필드 주입)
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/login")
    public String login(Model model) {
        return "popupStore/login"; // 로그인 화면 요청
    }
}

package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.entity.Users;
import com.ssginc.commonservice.popupStore.service.UsersService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/login2")
    public String login(Model model, HttpSession session, Users users) {
        // 로그인 정보가 잘 넘어오는지 확인하기 위한 출력문
        System.out.println("=====================");
        System.out.println(users);
        System.out.println("=====================");

        boolean result = usersService.login(users);

        if (result) {
            session.setAttribute("userEmail", users.getUserEmail());
        } else {
            model.addAttribute("userEmail", "이메일 주소와 비밀번호를 확인해주세요.");
            return "popupStore/login";
        }

        return "redirect:/popupStore/list"; // 로그인 성공 후 이동할 페이지(메인페이지)
        //redirect --> 클라이언트 호출이여서 @GetMapping("popupStore/list")를 호출
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userEmail"); // 세션에서 userEmail 제거
        System.out.println("삭제된 세션값 설정 완료 >> " + session.getAttribute("userEmail"));
        //session.getAttribute("userEmail") --> null 반환
        //삭제된 세션값 설정 완료 >> null
        return "redirect:/"; // 로그아웃 후 메인 페이지로 리다이렉트
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.info("create 요청됨.");
        return "popupStore/create"; // 회원가입 화면 요청
    }

    @PostMapping("/create2")
    public String create(Model model, Users users) {
        log.info("create2 요청됨.");
        System.out.println("===================");
        System.out.println(users);
        System.out.println("===================");
        model.addAttribute("users", usersService.createUser(users));
        return "popupStore/create2"; // 회원가입 처리, 처리 후 화면 요청
    }
}

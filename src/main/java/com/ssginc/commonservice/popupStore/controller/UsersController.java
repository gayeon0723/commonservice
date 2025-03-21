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
    private final UsersService usersService; // 사용자 관련 비즈니스 로직을 처리

    /**
     * 로그인 페이지 요청을 처리하는 메서드
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @return 로그인 페이지 경로 (Thymeleaf 템플릿 위치)
     */
    @GetMapping("/login")
    public String login(Model model) {
        return "popupStore/login"; // 로그인 페이지 반환
    }

    /**
     * 로그인 요청을 처리하는 메서드
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @param session 로그인 성공 시 사용자 정보를 저장하기 위한 세션 객체
     * @param users 로그인 시 입력받은 사용자 정보 (이메일, 비밀번호 등)
     * @return 로그인 성공 시 메인 페이지로 리다이렉트, 실패 시 다시 로그인 페이지로 이동
     */
    @PostMapping("/login2")
    public String login(Model model, HttpSession session, Users users) {
        // 사용자 정보가 잘 전달되는지 확인하기 위한 출력문
        System.out.println("=====================");
        System.out.println(users);
        System.out.println("=====================");

        boolean result = usersService.login(users); // 사용자 로그인 검증

        if (result) {
            // 로그인 성공 시 세션에 사용자 이메일 저장
            session.setAttribute("userEmail", users.getUserEmail());

            Users users1 = usersService.getUserInfo(users);
            System.out.println("===> 개인정보 : " + users1);
            // 세션에 사용자 정보 저장
            session.setAttribute("user1", users1);
            session.setAttribute("userName", users.getUserName());
        } else {
            // 로그인 실패 시 에러 메시지를 모델에 추가하여 로그인 페이지로 다시 이동
            model.addAttribute("userEmail", "이메일 주소와 비밀번호를 확인해주세요.");
            return "popupStore/login"; // 로그인 실패 시 로그인 페이지 반환
        }

        return "redirect:/popupStore/list"; // 로그인 성공 후 이동할 페이지(메인페이지)
        //redirect --> 클라이언트 호출이여서 @GetMapping("popupStore/list")를 호출
    }

    /**
     * 로그아웃 요청을 처리하는 메서드
     * @param session 현재 로그인한 사용자의 세션 정보
     * @return 로그아웃 후 메인 페이지로 리다이렉트
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 userEmail 속성 제거 (로그아웃 처리)
        session.removeAttribute("userEmail");
        System.out.println("삭제된 세션값 설정 완료 >> " + session.getAttribute("userEmail"));
        //session.getAttribute("userEmail") --> null 반환
        //삭제된 세션값 설정 완료 >> null
        return "redirect:/"; // 로그아웃 후 메인 페이지로 리다이렉트
    }

    /**
     * 회원가입 페이지 요청을 처리하는 메서드
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @return 회원가입 페이지 경로 (Thymeleaf 템플릿 위치)
     */
    @GetMapping("/create")
    public String create(Model model) {
        log.info("create 요청됨."); // 로그 출력 (회원가입 페이지 요청 확인)
        return "popupStore/create"; // 회원가입 페이지 반환
    }

    /**
     * 회원가입 요청을 처리하는 메서드
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @param users 회원가입 시 입력받은 사용자 정보
     * @return 회원가입 완료 후의 페이지 경로
     */
    @PostMapping("/create2")
    public String create(Model model, Users users) {
        log.info("create2 요청됨."); // 로그 출력 (회원가입 요청 확인)

        // 사용자 정보가 잘 전달되는지 확인하기 위한 출력문
        System.out.println("===================");
        System.out.println(users);
        System.out.println("===================");

        // 회원가입 처리 후 가입된 사용자 정보를 모델에 추가
        model.addAttribute("users", usersService.createUser(users));

        // 회원가입 완료 페이지 반환
        return "popupStore/create2";
    }
}

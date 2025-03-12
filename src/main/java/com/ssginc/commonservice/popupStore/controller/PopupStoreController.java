package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.entity.PopupStore;
import com.ssginc.commonservice.popupStore.service.PopupStoreService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j // 로깅을 위한 Lombok 어노테이션 (로그 출력 가능)
@Controller // Spring MVC의 컨트롤러로 지정 (View 반환)
@RequestMapping("/popupStore") // 기본 URL 매핑: /popupStore
@RequiredArgsConstructor // 생성자를 자동으로 만들어줌 (final 필드 주입)
public class PopupStoreController {
    private final PopupStoreService popupStoreService; // 팝업스토어 관련 서비스 (비즈니스 로직 처리)

    /**
     * 팝업스토어 조회
     * URL: GET /popupStore/list
     *
     * @param model Model 객체 (뷰에 데이터 전달)
     * @return "popupStore/list" (Thymeleaf 템플릿)
     */
    @GetMapping("/list")
    public String list(Model model, HttpSession httpSession) {
        // 팝업스토어 목록을 조회하여 화면에 전달하는 기능
        //list로 요청했을 때,
        //DB처리는 2개
        //1. 담당자 픽 서울 인기 팝업 2월
        //2. 방금 도착! NEW 팝업
        //===> list.html애서 1, 2의 결과를 출력하려면 model로 각각 설정해주어야 함.

        // (1) 모든 팝업스토어 조회
        List<PopupStore> list = popupStoreService.findAll(); // DB에서 모든 팝업스토어 데이터 조회
        System.out.println("==============" + list.size()); // 조회된 목록의 개수 출력 (디버깅 용도)
        System.out.println(list); // 조회된 팝업스토어 리스트 출력 (디버깅 용도)
        model.addAttribute("list", list); // 모델 객체에 조회 결과 추가 (뷰에서 사용 가능)

        // (2) 최근에 추가된 팝업스토어 조회
        List<PopupStore> list2 = popupStoreService.findRecent(); // DB에서 최신 팝업스토어 데이터 조회
        System.out.println("==============" + list2.size());
        System.out.println(list2);
        model.addAttribute("list2", list2);
        httpSession.setAttribute("userId", 1); //로그인 된 상태
        // Thymeleaf 템플릿 "popupStore/list.html"로 이동 (해당 페이지에서 데이터를 출력)
        return "popupStore/list"; // list.html 뷰 반환
    }

    /**
     * 특정 팝업스토어 상세 조회 페이지
     * - 사용자가 "/popupStore/detail"로 GET 요청을 하면 호출됨
     * - 운영 시작일이 가장 빠른 팝업스토어 1개를 조회하여 화면에 전달
     * - 조회된 데이터는 "popupStore/detail.html" 뷰에서 출력됨
     *
     * @param model Model 객체 (컨트롤러에서 뷰로 데이터를 전달하기 위해 사용)
     * @return "popupStore/detail" (Thymeleaf 템플릿)
     */
    @GetMapping("/detail")
    public String detail(Model model) {
        List<PopupStore> detail = popupStoreService.findInfo();
        model.addAttribute("detail", detail);
        // Thymeleaf 템플릿 "popupStore/detail.html"로 이동 (해당 페이지에서 데이터를 출력)
        return "popupStore/detail"; // detail.html 뷰 반환
    }

    /**
     * 팝업스토어 등록 페이지 이동 기능
     *
     * URL: GET /popupStore/register
     * 실행 흐름:
     *  1. "popupStore/register.html" 템플릿을 반환하여 화면에 출력
     *
     * @param model 뷰에 데이터를 전달하기 위한 객체 (현재는 데이터 없음)
     * @return "popupStore/register" (Thymeleaf 템플릿)
     */
    @GetMapping("/register")
    public String register(Model model) {
        return "popupStore/register"; //register.html 호출
    }

    /**
     * 팝업스토어 등록 처리 기능
     *
     * URL: POST /popupStore/register2
     * 실행 흐름:
     *  1. 클라이언트에서 전달된 팝업스토어 데이터를 받아 저장
     *  2. 저장된 데이터를 모델에 추가하여 뷰로 전달
     *  3. "popupStore/register2.html"로 이동하여 결과 출력
     *
     * @param model 뷰에 데이터를 전달하기 위한 객체
     * @return "popupStore/register2" (Thymeleaf 템플릿)
     */
    @PostMapping("/register2")
    public String register(Model model, PopupStore popupStore) {
        // (1) 입력된 팝업스토어 데이터 확인
        System.out.println(popupStore);
        System.out.println(popupStore.getStoreAt());

        // (2) DB에 팝업스토어 데이터 저장
        PopupStore register = popupStoreService.save(popupStore);

        // (3) 모델 객체에 저장된 데이터 추가 (뷰에서 사용 가능)
        model.addAttribute("register", register);

        // (4) "popupStore/register2.html"로 이동하여 결과 출력
        return "popupStore/register2";
    }
}

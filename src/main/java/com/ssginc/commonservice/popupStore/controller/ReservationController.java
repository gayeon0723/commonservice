package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.dto.ReservationDto;
import com.ssginc.commonservice.popupStore.entity.Reservation;
import com.ssginc.commonservice.popupStore.service.PopupStoreService;
import com.ssginc.commonservice.popupStore.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 예약 관련 컨트롤러
 * 예약 관련 요청을 처리하는 역할을 담당한다.
 */
@Slf4j // 로깅을 위한 Lombok 어노테이션 (로그 출력 가능)
@Controller // Spring MVC의 컨트롤러로 지정 (View 반환)
@RequestMapping("/popupStore") // 기본 URL 매핑: /popupStore
@RequiredArgsConstructor // 생성자를 자동으로 만들어줌 (final 필드 주입)
public class ReservationController {
    private final ReservationService reservationService; // 예약 관련 비즈니스 로직을 처리

    /**
     * 예약자 정보를 입력받는 페이지를 반환하는 메서드
     * @param model 뷰에 데이터를 전달하는 객체
     * @param storeId 팝업스토어의 고유 ID
     * @return 예약 화면 (popupStore/member.html)
     */
    @GetMapping("/member/{storeId}")
    public String member(Model model, @PathVariable("storeId") int storeId, HttpSession session) {
        // 세션을 활용하여 로그인한 사용자 정보를 가져옴 (디버깅용 출력)
        System.out.println("나를 호출--------------1");
        System.out.println("====================");
        System.out.println(session.getAttribute("user1")); // 세션에 저장된 사용자 정보
        System.out.println(session.getAttribute("userName")); // 세션에 저장된 사용자 이름
        System.out.println(storeId); // URL에서 받아온 storeId 확인
        System.out.println("====================");
        // 모델에 storeId 추가하여 뷰에서 사용할 수 있도록 함
        model.addAttribute("storeId", storeId);
        // 예약 정보 입력 화면 호출
        return "/popupStore/member";
    }

    /**
     * 예약 정보를 저장하고 예약 완료 페이지를 반환하는 메서드
     * @param model 뷰에 데이터를 전달하는 객체
     * @param reservationDto 사용자가 입력한 예약 정보 (DTO 객체)
     * @return 예약 완료 화면 (popupStore/member2.html)
     */
    @PostMapping("/member2") // 예약 처리 요청을 처리하는 핸들러
    public String member2(Model model, ReservationDto reservationDto) {
        System.out.println("나를 호출--------------2");
        // 예약 정보가 잘 넘어오는지 확인하기 위한 출력문
        System.out.println("===================");
        System.out.println(reservationDto);
        System.out.println("===================");

        //전달용은 Dto로 받고
        //entity로 옮기자.
        //==> DTO를 Entity로 변환 (DB에 저장하기 위해)
        Reservation reservation = Reservation.builder()
                .userId(reservationDto.getUserId()) // 사용자 ID 설정
                .storeId(reservationDto.getStoreId()) // 팝업스토어 ID 설정
                .userEmail(reservationDto.getUserEmail()) // 사용자 이메일 설정
                .reservationDatetime(reservationDto.getReservationDatetime()) // 예약 일시 설정
                .build(); // Lombok의 @Builder 어노테이션을 사용하여 객체를 생성하는 메서드
        // 엔티티 객체가 잘 만들어졌는지 확인
        System.out.println(reservation);
        // 예약 정보를 저장하고 반환받음
        Reservation member = reservationService.save(reservation);

        // 저장된 예약 정보를 뷰로 전달
        model.addAttribute("reservation", reservation);
        // 예약 완료 페이지 반환
        return "/popupStore/member2";
    }
}

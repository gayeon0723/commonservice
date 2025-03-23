package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.dto.ChartDto;
import com.ssginc.commonservice.popupStore.service.ChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j // 로깅을 위한 Lombok 어노테이션 (로그 출력 가능)
@Controller // Spring MVC의 컨트롤러로 지정 (View 반환)
@RequiredArgsConstructor // 생성자를 자동으로 만들어줌 (final 필드 주입)
public class ChartController {
    private final ChartService chartService; // Chart 데이터를 제공하는 서비스 객체

    //팝업스토어 예약 현황 json으로 가지고 오는 컨트롤러
    @GetMapping("/reservation/chart2")
    @ResponseBody // 반환 데이터를 JSON 형태로 응답
    public List<ChartDto> chart(Model model) {
        List<ChartDto> chart = chartService.findByStoreId(); // 서비스에서 예약 데이터 조회
        System.out.println(chart); // 콘솔에 데이터 출력 (디버깅용)
        System.out.println(chart.size()); // 데이터 개수 출력 (디버깅용)
        return chart; // JSON 형태로 반환
    }

    //팝업스토어 예약 현황 json으로 가지고 오는 컨트롤러
    @GetMapping("/reservation/chart3")
    @ResponseBody // 반환 데이터를 JSON 형태로 응답
    public List<ChartDto> chart3(Model model) {
        List<ChartDto> chart = chartService.findByStoreId(); // 서비스에서 예약 데이터 조회
        System.out.println(chart); // 콘솔에 데이터 출력 (디버깅용)
        return chart; // JSON 형태로 반환
    }

    //popupStore.html을 부르기 위한 컨트롤러
    @GetMapping("/popupStore/chart")
    public String chart2(Model model) {
        return "popupStore/chart";
    }
}

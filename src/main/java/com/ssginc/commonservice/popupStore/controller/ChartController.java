package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.dto.ChartDto;
import com.ssginc.commonservice.popupStore.service.ChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChartController {
    private final ChartService chartService;

    //팝업스토어 예약 현황 json으로 가지고 오는 컨트롤러
    @GetMapping("/reservation/chart2")
    @ResponseBody
    public List<ChartDto> chart(Model model) {
        List<ChartDto> chart = chartService.findByStoreId();
        System.out.println(chart);
        System.out.println(chart.size());
        return chart;
    }

    //팝업스토어 예약 현황 json으로 가지고 오는 컨트롤러
    @GetMapping("/reservation/chart3")
    @ResponseBody
    public List<ChartDto> chart3(Model model) {
        List<ChartDto> chart = chartService.findByStoreId();
        System.out.println(chart);
        return chart;
    }

    //popupStore.html을 부르기 위한 컨트롤러
    @GetMapping("/popupStore/chart")
    public String chart2(Model model) {
        return "popupStore/chart";
    }
}

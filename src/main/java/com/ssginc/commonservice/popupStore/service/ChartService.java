package com.ssginc.commonservice.popupStore.service;

import com.ssginc.commonservice.popupStore.dto.ChartDto;
import com.ssginc.commonservice.popupStore.repository.ChartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 해당 클래스를 Spring의 Service Bean으로 등록
@RequiredArgsConstructor // final 필드를 자동으로 주입하는 Lombok 어노테이션
public class ChartService {

    private final ChartRepository chartRepository; // 데이터베이스에서 차트 데이터를 가져오는 Repository

    /**
     * 특정 팝업스토어의 예약 현황 데이터를 조회
     * @return ChartDto 리스트 (예약 현황 정보)
     */
    public List<ChartDto> findByStoreId() {
        return chartRepository.findByStoreId(); // ChartRepository를 통해 예약 데이터를 조회하여 반환
    }
}

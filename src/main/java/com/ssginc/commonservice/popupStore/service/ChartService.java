package com.ssginc.commonservice.popupStore.service;

import com.ssginc.commonservice.popupStore.dto.ChartDto;
import com.ssginc.commonservice.popupStore.repository.ChartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChartService {

    private final ChartRepository chartRepository;

    //팝업스토어 예약 현황
    public List<ChartDto> findByStoreId() {
        return chartRepository.findByStoreId();
    }
}

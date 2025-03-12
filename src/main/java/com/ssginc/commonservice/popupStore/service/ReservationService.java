package com.ssginc.commonservice.popupStore.service;

import com.ssginc.commonservice.popupStore.entity.Reservation;
import com.ssginc.commonservice.popupStore.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 예약 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service // 해당 클래스를 Spring의 Service Bean으로 등록
@RequiredArgsConstructor // final 필드를 자동으로 주입하는 Lombok 어노테이션
public class ReservationService {

    private final ReservationRepository reservationRepository; // 팝업스토어 예약 데이터 접근 객체 (Repository)

    /**
     * 예약 정보를 저장하는 메서드
     * @param reservation 저장할 예약 객체
     * @return 저장된 예약 객체
     */
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}

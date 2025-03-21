package com.ssginc.commonservice.popupStore.service;

import com.ssginc.commonservice.popupStore.entity.Reservation;
import com.ssginc.commonservice.popupStore.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 예약 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service // 해당 클래스를 Spring의 Service Bean으로 등록
@RequiredArgsConstructor // final 필드를 자동으로 주입하는 Lombok 어노테이션
public class ReservationService {

    private final ReservationRepository reservationRepository; // 팝업스토어 예약 데이터 접근 객체 (Repository)

    /**
     * 예약 정보를 저장하는 메서드
     * - 특정 조건에 맞는 예약 정보를 데이터베이스에서 가져옴.
     * - 트랜잭션이 필요하므로 @Transactional 어노테이션을 사용.
     *
     * @return 조회된 예약 정보 (Reservation 객체)
     */

    @Transactional // 데이터 변경이 일어나므로 트랜잭션 적용 (자동 롤백/커밋 관리)
    public Reservation findReservation() {
        return reservationRepository.findReservationBy();
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}

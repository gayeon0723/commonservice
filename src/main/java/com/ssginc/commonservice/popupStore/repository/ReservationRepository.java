package com.ssginc.commonservice.popupStore.repository;

import com.ssginc.commonservice.popupStore.entity.Reservation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 예약 엔티티에 대한 데이터 액세스 계층 (Repository)
 * Spring Data JPA를 사용하여 데이터베이스와 상호작용하는 인터페이스
 */
@Repository // 해당 인터페이스가 Spring의 Repository 컴포넌트임을 명시
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // JpaRepository를 상속받아 기본적인 CRUD 기능을 자동으로 제공
    // JpaRepository<엔티티 타입, 기본 키 타입>
    // 별도의 메서드를 정의하지 않아도 findAll(), findById(), save(), delete() 등의 기능을 사용할 수 있음

    @Lock(LockModeType.PESSIMISTIC_WRITE) // 비관적 락 적용 (쓰기 잠금)
    @Query("select r from Reservation r") // JPQL을 사용하여 예약 정보 조회
    public Reservation findReservationBy();
}

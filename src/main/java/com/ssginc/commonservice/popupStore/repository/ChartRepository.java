package com.ssginc.commonservice.popupStore.repository;

import com.ssginc.commonservice.popupStore.dto.ChartDto;
import com.ssginc.commonservice.popupStore.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 해당 인터페이스가 Spring의 Repository 컴포넌트임을 명시
public interface ChartRepository extends JpaRepository<Reservation, Long> {

    /**
     * 팝업스토어별 예약 건수를 조회하는 쿼리
     *
     * @return ChartDto 리스트 (storeId별 예약 건수 포함)
     */
    @Query("""
        SELECT new com.ssginc.commonservice.popupStore.dto.ChartDto 
        (r.storeId, COUNT(r))  
        FROM Reservation r
        GROUP BY r.storeId
    """)
    List<ChartDto> findByStoreId();
}

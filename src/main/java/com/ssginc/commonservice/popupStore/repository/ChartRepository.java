package com.ssginc.commonservice.popupStore.repository;

import com.ssginc.commonservice.popupStore.dto.ChartDto;
import com.ssginc.commonservice.popupStore.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChartRepository extends JpaRepository<Reservation, Long> {

    //팝업스토어 예약 현황
    @Query("""
        SELECT new com.ssginc.commonservice.popupStore.dto.ChartDto 
        (r.storeId, COUNT(r))  
        FROM Reservation r
        GROUP BY r.storeId
    """)
    List<ChartDto> findByStoreId();
}

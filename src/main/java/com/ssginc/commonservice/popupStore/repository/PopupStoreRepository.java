package com.ssginc.commonservice.popupStore.repository;

import com.ssginc.commonservice.popupStore.entity.PopupStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 팝업스토어 엔티티에 대한 데이터 액세스 계층 (Repository)
 * Spring Data JPA를 사용하여 데이터베이스와 상호작용하는 인터페이스
 */
@Repository // 해당 인터페이스가 Spring의 Repository 컴포넌트임을 명시
public interface PopupStoreRepository extends JpaRepository<PopupStore, Long>{
    // JpaRepository를 상속받아 기본적인 CRUD 기능을 자동으로 제공
    // JpaRepository<엔티티 타입, 기본 키 타입>
    // 별도의 메서드를 정의하지 않아도 findAll(), findById(), save(), delete() 등의 기능을 사용할 수 있음

    /**
     * 인기 팝업스토어 조회
     * - 현재 예약 가능 인원(nowCapacity)이 많은 순서대로 정렬
     * - 상위 3개의 팝업스토어를 조회
     */
    @Query("""
        SELECT p FROM PopupStore p
        ORDER BY p.nowCapacity DESC
        LIMIT 3
    """)
    List<PopupStore> findAll2(); // 예약 가능 인원이 많은 순서대로 상위 3개 조회

    /**
     * 최근에 오픈한 팝업스토어 조회
     * - 운영 시작일(storeStart)이 최신순으로 정렬된 팝업스토어를 조회
     * - 상위 3개의 팝업스토어를 가져옴
     */
    @Query("""
        SELECT p FROM PopupStore p
        ORDER BY p.storeStart DESC
        LIMIT 3
    """)
    List<PopupStore> findRecent2(); // 최근에 오픈한 팝업스토어 3개 조회

    /**
     * 팝업스토어 상세 정보 조회
     * - 임의로 팝업스토어 리스트 1개 가져옴
     */
    @Query("""
        SELECT p FROM PopupStore p
        ORDER BY p.storeStart
        LIMIT 1
    """)
    List<PopupStore> findInfo();
}

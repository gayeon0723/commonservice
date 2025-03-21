package com.ssginc.commonservice.popupStore.service;

import com.ssginc.commonservice.popupStore.entity.PopupStore;
import com.ssginc.commonservice.popupStore.repository.PopupStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 팝업스토어 서비스 클래스
 * - 팝업스토어 관련 비즈니스 로직을 처리하는 서비스 계층
 */
@Service // 해당 클래스를 Spring의 Service Bean으로 등록
@RequiredArgsConstructor // final 필드를 자동으로 주입하는 Lombok 어노테이션
public class PopupStoreService {

    private final PopupStoreRepository popupStoreRepository; // 팝업스토어 데이터 접근 객체 (Repository)

    /**
     * 모든 팝업스토어 목록을 조회하는 메서드
     * - Repository에서 정의한 findAll2() 메서드를 호출하여 인기 팝업스토어를 가져옴
     *
     * @return List<PopupStore> - DB에 저장된 모든 팝업스토어 리스트
     */
    public List<PopupStore> findAll() {
        return popupStoreRepository.findAll2(); // 인기 팝업스토어 조회 (현재 예약 인원 순으로 정렬)
    }

    /**
     * 최근에 추가된 팝업스토어 목록을 조회하는 메서드
     * - Repository에서 정의한 findRecent2() 메서드를 호출하여 최신 팝업스토어를 가져옴
     *
     * @return List<PopupStore> - 최근 등록된 팝업스토어 리스트
     */
    public List<PopupStore> findRecent() {
        return popupStoreRepository.findRecent2(); // 최근에 등록된 팝업스토어 조회 (등록일 기준 정렬)
    }

    /**
     * 팝업스토어 상세 정보 조회
     * - 임의로 팝업스토어 리스트 1개 가져옴
     */

    public List<PopupStore> findInfo(Long storeId) {
        return popupStoreRepository.findInfo(storeId);
    }

    /**
     * 새로운 팝업스토어 정보를 저장하는 메서드
     * - 사용자가 등록한 팝업스토어 정보를 DB에 저장
     *
     * @param popupStore - 저장할 팝업스토어 객체 (사용자로부터 입력받은 데이터)
     * @return PopupStore - 저장된 팝업스토어 객체 (DB에 저장된 후 반환)
     */
    public PopupStore save(PopupStore popupStore) {
        return popupStoreRepository.save(popupStore); // JPA를 사용하여 저장
    }

    /**
     * 팝업스토어 검색 기능 (인기 팝업스토어 검색)
     * - 키워드를 포함하는 인기 팝업스토어 리스트를 조회함
     *
     * @param keyword - 검색어 (사용자가 입력한 키워드)
     * @return List<PopupStore> - 검색 결과 리스트 반환
     */
    public List<PopupStore> getListByContent(String keyword) {
        return popupStoreRepository.getListByContent(keyword); // 키워드를 포함한 인기 팝업스토어 조회
    }

    /**
     * 팝업스토어 검색 기능 (방금 도착한 신규 팝업스토어 검색)
     * - 키워드를 포함하는 최근 추가된 팝업스토어 리스트를 조회함
     *
     * @param keyword - 검색어 (사용자가 입력한 키워드)
     * @return List<PopupStore> - 검색 결과 리스트 반환
     */
    public List<PopupStore> getListByContent2(String keyword) {
        return popupStoreRepository.getListByContent2(keyword); // 키워드를 포함한 신규 팝업스토어 조회
    }
}

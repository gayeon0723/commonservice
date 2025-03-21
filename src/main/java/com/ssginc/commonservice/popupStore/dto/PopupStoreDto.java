package com.ssginc.commonservice.popupStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter // 모든 필드에 대해 Getter 자동 생성
@NoArgsConstructor // Lombok: 기본 생성자 자동 생성
@AllArgsConstructor // Lombok: 모든 필드를 포함한 생성자 자동 생성
@Builder // Lombok: 빌더 패턴 자동 생성
public class PopupStoreDto {
    private String storeName; //팝업스토어명
    private String storeType; //업종
    private String storeAt; //위치
    private String storeCategory; //카테고리
    private String storeThumbnail; //팝업스토어 이미지
    private String storeThumbnail2; //팝업스토어 이미지
    private String storeThumbnail3; //팝업스토어 이미지
    private String storeMap; //팝업스토어 위치 지도
    private LocalDateTime storeStart; //운영 시작일시
    private LocalDateTime storeEnd; //운영 종료일시
    private String storeDesc; //팝업스토어 상세 설명
}

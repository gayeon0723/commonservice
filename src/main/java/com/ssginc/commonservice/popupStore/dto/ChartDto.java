package com.ssginc.commonservice.popupStore.dto;

import lombok.*;

@Getter // 모든 필드에 대해 Getter 자동 생성
@NoArgsConstructor // Lombok: 기본 생성자 자동 생성
@AllArgsConstructor // Lombok: 모든 필드를 포함한 생성자 자동 생성
@Builder // Lombok: 빌더 패턴 자동 생성
@ToString // Lombok: toString() 자동 생성
          // 실행 시 자동으로 해당 엔티티의 모든 필드 값을 출력
public class ChartDto {
    private Long storeId; //팝업스토어 ID
    private Long reservationCount; //팝업스토어 예약 건수
}

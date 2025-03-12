package com.ssginc.commonservice.popupStore.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 모두 자동 생성
@NoArgsConstructor // Lombok: 기본 생성자 자동 생성
@AllArgsConstructor // Lombok: 모든 필드를 포함한 생성자 자동 생성
@Builder // Lombok: 빌더 패턴 자동 생성
public class ReservationDto {
    private Long storeId; //팝업스토어 ID
    private Long userId; //사용자 ID
    private String userEmail; //사용자 이메일
    private LocalDateTime reservationDatetime; //예약 일시
}

package com.ssginc.commonservice.popupStore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter // 모든 필드에 대해 Getter 자동 생성
@NoArgsConstructor // Lombok: 기본 생성자 자동 생성
@AllArgsConstructor // Lombok: 모든 필드를 포함한 생성자 자동 생성
@Builder // Lombok: 빌더 패턴 자동 생성
@Entity // JPA 엔티티 선언 (해당 클래스가 DB 테이블과 매핑됨)
@ToString // Lombok: toString() 자동 생성
          // 실행 시 자동으로 해당 엔티티의 모든 필드 값을 출력
public class Reservation {

    @Id // 기본 키(PK) 설정
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 (IDENTITY: DB가 자동으로 증가값을 부여)
    private Long reservationId; //예약 ID

    private Long storeId; //팝업스토어 ID
    private Long userId; //사용자 ID

    @Column(nullable = false, length = 500) // null 불가, 최대 길이 500자
    private String userEmail; //사용자 이메일

    @Column(nullable = false) // null 불가
    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    private LocalDateTime reservationDatetime; //예약 일시
}

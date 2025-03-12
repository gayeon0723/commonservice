package com.ssginc.commonservice.popupStore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 팝업스토어 엔티티 클래스
 */

//@Getter // 모든 필드에 대해 Getter 자동 생성
@NoArgsConstructor // Lombok: 기본 생성자 자동 생성
@AllArgsConstructor // Lombok: 모든 필드를 포함한 생성자 자동 생성
@Builder // Lombok: 빌더 패턴 자동 생성
@Entity // JPA 엔티티 선언 (해당 클래스가 DB 테이블과 매핑됨)
@Table(name = "popupstore") // 해당 엔티티가 "popupstore" 테이블과 매핑됨을 명시
@Data
public class PopupStore {

    @Id // 기본 키(PK) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 (IDENTITY: DB가 자동으로 증가값을 부여)
    private Long storeId; //팝업스토어 ID

    @Column(nullable = false, length = 500) // null 불가, 최대 길이 500자
    private String storeName; //팝업스토어명

    @Column(nullable = false, length = 100) // null 불가, 최대 길이 100자
    private String storeType; //업종

    @Column(nullable = false, length = 300) // null 불가, 최대 길이 300자
    private String storeAt; //위치

    @Column(nullable = false, length = 100) // null 불가, 최대 길이 100자
    private String storeCategory; //카테고리

    @Column(nullable = true)
    private LocalDateTime storeStart; //운영 시작일시

    @Column(nullable = true)
    private LocalDateTime storeEnd; //운영 종료일시

    @Column(nullable = false, length = 1000) // null 불가, 최대 길이 1000자
    private String storeDesc; //팝업스토어 상세 설명

    @Column(nullable = true, length = 500) // 최대 길이 500자
    private String storeThumbnail; //팝업스토어 이미지(썸네일 이미지, 파일 경로 또는 URL)

    private int maxCapacity; //에약 가능 인원
    private int nowCapacity; //현재 예약 가능 인원

    @Column(nullable = true)
    private Timestamp createdAt; //생성일(데이터 생성 시각)
}

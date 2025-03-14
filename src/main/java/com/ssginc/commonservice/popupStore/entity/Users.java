package com.ssginc.commonservice.popupStore.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter // 모든 필드에 대해 Getter 자동 생성
@NoArgsConstructor // Lombok: 기본 생성자 자동 생성
@AllArgsConstructor // Lombok: 모든 필드를 포함한 생성자 자동 생성
@Builder // Lombok: 빌더 패턴 자동 생성
@Entity // JPA 엔티티 선언 (해당 클래스가 DB 테이블과 매핑됨)
@ToString // Lombok: toString() 자동 생성
          // 실행 시 자동으로 해당 엔티티의 모든 필드 값을 출력
@Setter // 모든 필드에 대해 Setter 자동 생성
public class Users {

    @Id // 기본 키(PK) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 (IDENTITY: DB가 자동으로 증가값을 부여)
    private Long userId; //사용자 ID

    @Column(nullable = false, length = 500) // null 불가, 최대 길이 500자
    private String userEmail; //사용자 이메일

    @Column(nullable = false, length = 500) // null 불가, 최대 길이 500자
    private String password; //사용자 비밀번호

    @Column(nullable = false, length = 500) // null 불가, 최대 길이 500자
    private String userName; //사용자 이름

    @Column(nullable = false, length = 250) // null 불가, 최대 길이 250자
    private String userPhone; //사용자 연락처
}


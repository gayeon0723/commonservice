package com.ssginc.commonservice.popupStore.dto;

import lombok.*;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 모두 자동 생성
@NoArgsConstructor // Lombok: 기본 생성자 자동 생성
@AllArgsConstructor // Lombok: 모든 필드를 포함한 생성자 자동 생성
@Builder // Lombok: 빌더 패턴 자동 생성
public class UsersDto {
    private int userId; //사용자 ID
    private String userEmail; //사용자 이메일
    private String password; //사용자 비밀번호
    private String userName; //사용자 이름
    private String userPhone; //사용자 연락처
}

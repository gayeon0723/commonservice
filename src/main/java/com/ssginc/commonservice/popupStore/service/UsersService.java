package com.ssginc.commonservice.popupStore.service;

import com.ssginc.commonservice.popupStore.entity.Users;
import com.ssginc.commonservice.popupStore.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // 해당 클래스를 Spring의 Service Bean으로 등록
@RequiredArgsConstructor // final 필드를 자동으로 주입하는 Lombok 어노테이션
public class UsersService {

    private final UsersRepository usersRepository; // 사용자 관련 데이터베이스 처리를 위한 Repository
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위한 PasswordEncoder (Spring Security)

    /**
     * 사용자의 로그인 검증 메서드
     * @param users 사용자가 입력한 로그인 정보 (이메일, 비밀번호)
     * @return 로그인 성공 여부 (true: 성공, false: 실패)
     */
    public boolean login(Users users) {
        //Users user = usersRepository.login(users.getUserEmail(), users.getPassword());
        // 이메일을 기반으로 DB에서 사용자 정보 조회
        Users users1 = usersRepository.login(users.getUserEmail());
        // 조회된 사용자 정보 출력
        System.out.println(users1);

        // 사용자가 존재하는 경우 비밀번호 검증
        if (users1 != null) {
            // 입력한 비밀번호와 DB에 저장된 암호화된 비밀번호 비교
            if (passwordEncoder.matches(users.getPassword(), users1.getPassword())) {
                return true; // 로그인 성공
            } else {
                return false; // 로그인 실패 (비밀번호 불일치)
            }
        }
        return false; // 로그인 실패 (사용자 존재하지 않음)
    }

    /**
     * 회원가입 처리 메서드
     * @param users 사용자가 입력한 회원가입 정보 (이메일, 비밀번호 등)
     * @return 저장된 사용자 객체
     */
    public Users createUser(Users users) {
        // 입력받은 비밀번호를 암호화하여 저장 (보안 강화)
        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword); // 암호화된 비밀번호 설정
        // 암호화된 비밀번호 확인
        System.out.println(users.getPassword());
        // 사용자 정보 DB에 저장
        usersRepository.save(users); //save() 메서드 - 엔티티를 저장하거나 수정할 때 사용하는 메서드
        return users; // 저장된 사용자 객체 반환
    }

    /**
     * 사용자 정보 조회 메서드
     * - 특정 사용자의 정보를 가져옴 (이메일 기반 검색)
     * @param users 조회할 사용자 객체 (이메일 필요)
     * @return 조회된 사용자 정보 객체
     */
    public Users getUserInfo(Users users) {
        return usersRepository.login(users.getUserEmail()); // 이메일 기반 사용자 정보 조회
    }
}

package com.ssginc.commonservice.popupStore.service;

import com.ssginc.commonservice.popupStore.entity.Users;
import com.ssginc.commonservice.popupStore.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // 해당 클래스를 Spring의 Service Bean으로 등록
@RequiredArgsConstructor // final 필드를 자동으로 주입하는 Lombok 어노테이션
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    //로그인
    public boolean login(Users users) {
        //Users user = usersRepository.login(users.getUserEmail(), users.getPassword());
        Users users1 = usersRepository.login(users.getUserEmail());
        System.out.println(users1);
        if (users1 != null) {
            if (passwordEncoder.matches(users.getPassword(), users1.getPassword())) {
                return true; //로그인 성공
            } else {
                return false; //로그인 실패
            }
        }
        return false; //로그인 실패
    }

    //회원가입
    public Users createUser(Users users) {
        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        System.out.println(users.getPassword());
        usersRepository.save(users); //save() 메서드 - 엔티티를 저장하거나 수정할 때 사용하는 메서드
        return users;
    }
}

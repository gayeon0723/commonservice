package com.ssginc.commonservice.popupStore.service;

import com.ssginc.commonservice.popupStore.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 해당 클래스를 Spring의 Service Bean으로 등록
@RequiredArgsConstructor // final 필드를 자동으로 주입하는 Lombok 어노테이션
public class UsersService {

    private final UsersRepository usersRepository;
}

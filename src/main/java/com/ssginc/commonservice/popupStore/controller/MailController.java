package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로깅을 위한 Lombok 어노테이션 (로그 출력 가능)
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor // 생성자를 자동으로 만들어줌 (final 필드 주입)
public class MailController {
    private final MailService mailService;

    // 이메일 인증코드 발송
    @PostMapping("sendCode")
    public String sendEmailVerificationCode(String userEmail) {
        // 1. 인증 코드 생성
        String authCode = mailService.createAuthCode();
        // 2. 이메일 발송
        try {
            mailService.sendVerificationMail(userEmail, authCode);
        } catch (MessagingException e) {
            log.error("이메일 발송 실패:", e);
            return "fail";
        }
        return "success";
    }

    // 이메일 인증코드 검증
    @PostMapping("verifyCode")
    public String verifyEmailCode(String userEmail,
                                  String authCode) {
        if (mailService.verifyAuthCode(userEmail, authCode)) {
            return "verified"; // 인증 성공
        } else {
            return "invalid"; // 인증 실패
        }
    }
}

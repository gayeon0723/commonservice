package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로깅을 위한 Lombok 어노테이션 (로그 출력 가능)
@RestController // 해당 클래스를 REST 컨트롤러로 지정 (JSON 반환)
@RequestMapping("/email") // 기본 URL 경로 설정 (이 컨트롤러의 모든 엔드포인트는 '/email'을 포함)
@RequiredArgsConstructor // 생성자를 자동으로 만들어줌 (final 필드 주입)
public class MailController {
    private final MailService mailService; // 이메일 관련 서비스 (인증 코드 생성, 이메일 발송, 인증 검증)

    /**
     * 이메일 인증 코드 발송
     * @param userEmail 사용자의 이메일 주소
     * @return "success" (발송 성공) 또는 "fail" (발송 실패)
     */
    @PostMapping("sendCode") // HTTP POST 요청을 '/email/sendCode' 경로에서 처리
    public String sendEmailVerificationCode(String userEmail) {
        // 1. 인증 코드 생성
        String authCode = mailService.createAuthCode();
        // 2. 이메일 발송
        try {
            mailService.sendVerificationMail(userEmail, authCode);
        } catch (MessagingException e) {
            log.error("이메일 발송 실패:", e); // 오류 발생 시 로그 출력
            return "fail"; // 이메일 발송 실패 시 "fail" 반환
        }
        return "success"; // 이메일 발송 성공 시 "success" 반환
    }

    /**
     * 이메일 인증 코드 검증
     * @param userEmail 사용자의 이메일 주소
     * @param authCode 사용자가 입력한 인증 코드
     * @return "verified" (인증 성공) 또는 "invalid" (인증 실패)
     */
    @PostMapping("verifyCode") // HTTP POST 요청을 '/email/verifyCode' 경로에서 처리
    public String verifyEmailCode(String userEmail,
                                  String authCode) {
        // 사용자가 입력한 인증 코드가 올바른지 검증
        if (mailService.verifyAuthCode(userEmail, authCode)) {
            return "verified"; // 인증 성공
        } else {
            return "invalid"; // 인증 실패
        }
    }
}

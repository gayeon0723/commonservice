package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    // 이메일 인증코드 발송
    @PostMapping("sendCode")
    public String sendEmailVerificationCode(@RequestParam String userEmail) {
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
    public String verifyEmailCode(@RequestParam String userEmail,
                                  @RequestParam String authCode) {
        if (mailService.verifyAuthCode(userEmail, authCode)) {
            return "verified"; // 인증 성공
        } else {
            return "invalid"; // 인증 실패
        }
    }
}

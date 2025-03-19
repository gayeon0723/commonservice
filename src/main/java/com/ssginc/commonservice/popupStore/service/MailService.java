package com.ssginc.commonservice.popupStore.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    // 이메일-인증 코드 저장소 (인증 코드와 생성 시간을 함께 저장)
    private final Map<String, AuthCodeInfo> authCodeMap = new ConcurrentHashMap<>();

    // 랜덤 인증 코드 생성
    public String createAuthCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        // 6자리 숫자 코드 예시
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // 인증 코드 이메일 발송
    public void sendVerificationMail(String toEmail, String authCode) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        // true: 멀티파트 메세지
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("ssginc55@gmail.com"); // 보내는 사람
        helper.setTo(toEmail); // 받는 사람
        helper.setSubject("회원가입 이메일 인증 코드"); // 메일 제목
        // 메일 내용 (HTML 가능)
        helper.setText(
                "<h1>이메일 인증</h1>" +
                        "<p>아래 인증 코드를 회원가입 창에 입력하세요.</p>" +
                        "<h3>" + authCode + "</h3>",
                true
        );

        mailSender.send(message);

        // 인증 코드 저장 (현재 시간과 함께)
        authCodeMap.put(toEmail, new AuthCodeInfo(authCode, LocalDateTime.now()));
    }

    // 인증 코드 검증 (이메일과 코드가 일치하고, 유효시간 내인지 확인)
    public boolean verifyAuthCode(String email, String inputCode) {
        if (!authCodeMap.containsKey(email)) {
            return false; // 인증 코드가 존재하지 않음
        }
        AuthCodeInfo authInfo = authCodeMap.get(email);

        // 5분 초과 시 인증 코드 만료
        if (authInfo.getCreatedTime().plusMinutes(5).isBefore(LocalDateTime.now())) {
            authCodeMap.remove(email); // 만료된 코드 삭제
            return false;
        }

        return authInfo.getCode().equals(inputCode);
    }

    // 내부 클래스로 인증 코드 정보 저장
    private static class AuthCodeInfo {
        private final String code;
        private final LocalDateTime createdTime;

        public AuthCodeInfo(String code, LocalDateTime createdTime) {
            this.code = code;
            this.createdTime = createdTime;
        }

        public String getCode() {
            return code;
        }

        public LocalDateTime getCreatedTime() {
            return createdTime;
        }
    }
}

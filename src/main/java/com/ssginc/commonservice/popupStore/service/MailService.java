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

@Service // 해당 클래스를 Spring의 Service Bean으로 등록
@RequiredArgsConstructor // final 필드를 자동으로 주입하는 Lombok 어노테이션
public class MailService {
    private final JavaMailSender mailSender; // 이메일 전송을 위한 JavaMailSender 객체
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

        mailSender.send(message); // 이메일 전송 실행

        // 인증 코드 저장 (현재 시간과 함께)
        authCodeMap.put(toEmail, new AuthCodeInfo(authCode, LocalDateTime.now()));
    }

    // 인증 코드 검증 (이메일과 코드가 일치하고, 유효시간 내인지 확인)
    public boolean verifyAuthCode(String email, String inputCode) {
        // 이메일에 해당하는 인증 코드가 저장되어 있는지 확인
        if (!authCodeMap.containsKey(email)) {
            return false; // 인증 코드가 존재하지 않음
        }
        AuthCodeInfo authInfo = authCodeMap.get(email);

        // 5분 초과 시 인증 코드 만료
        if (authInfo.getCreatedTime().plusMinutes(5).isBefore(LocalDateTime.now())) {
            authCodeMap.remove(email); // 만료된 코드 삭제
            return false;
        }

        // 저장된 인증 코드와 사용자가 입력한 코드가 일치하는지 확인
        return authInfo.getCode().equals(inputCode);
    }

    // 내부 클래스로 인증 코드 정보 저장
    private static class AuthCodeInfo {
        private final String code; // 인증 코드
        private final LocalDateTime createdTime; // 인증 코드 생성 시간

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

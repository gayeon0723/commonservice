package com.ssginc.commonservice;

import com.ssginc.commonservice.popupStore.entity.PopupStore;
import com.ssginc.commonservice.popupStore.service.PopupStoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PopupStoreServiceTest {
    @Autowired
    private PopupStoreService popupStoreService;

    @Test
    public void testSave() {
        // 테스트 : 등록할 팝업스토어 생성 및 저장
        PopupStore register = PopupStore.builder()
                .storeName("Vintage Market")
                .storeType("Fashion")
                .storeAt("Seoul, Korea")
                .storeCategory("Clothing")
                .storeDesc("A vintage clothing pop-up.")
                .build();

        PopupStore result = popupStoreService.save(register);

        System.out.println("======================");
        System.out.println(result);
        System.out.println("======================");

        // 검증
        assertEquals("Vintage Market", result.getStoreName());
        assertEquals("Fashion", result.getStoreType());
    }
}

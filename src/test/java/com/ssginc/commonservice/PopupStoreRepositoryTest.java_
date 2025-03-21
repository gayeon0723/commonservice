package com.ssginc.commonservice;

import com.ssginc.commonservice.popupStore.entity.PopupStore;
import com.ssginc.commonservice.popupStore.repository.PopupStoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PopupStoreRepositoryTest {
    @Autowired
    private PopupStoreRepository popupStoreRepository;

    @Test
    public void testFindAll2() {
        // 테스트 : 모든 팝업스토어 목록을 제대로 가져오는지 확인
        List<PopupStore> result = popupStoreRepository.findAll2();

        System.out.println("===========================");
        result.forEach(System.out::println);
        System.out.println("===========================");

        // 검증
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(7, result.get(0).getStoreId());
    }
}

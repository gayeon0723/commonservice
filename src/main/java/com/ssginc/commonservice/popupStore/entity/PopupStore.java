package com.ssginc.commonservice.popupStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PopupStore {
    @jakarta.persistence.Id
    @Column(name = "id")
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column
    private int storeCategoryId;
    private String storeName;
    private String storeCategory;
    private String storeAt;
    private String storeShortDesc;
    private String storeLongDesc;
    private Date storeStart;
    private Date storeEnd;
    private Date createdAt;


}

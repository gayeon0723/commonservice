package com.ssginc.commonservice.popupStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PopupStoreDto {
    public Long storeId;
    public String storeName;
    public String storeCategory;
    public String storeAt;
    public String storeShortDesc;
    public String storeLongDesc;
    public String storeStart;
    public String storeEnd;
}

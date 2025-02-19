package com.ssginc.commonservice.popupStore.repository;

import com.ssginc.commonservice.popupStore.entity.PopupStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopupStoreRepository extends JpaRepository<PopupStore, Long>{
}

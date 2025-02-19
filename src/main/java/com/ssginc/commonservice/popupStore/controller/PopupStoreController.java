package com.ssginc.commonservice.popupStore.controller;

import com.ssginc.commonservice.popupStore.entity.PopupStore;
import com.ssginc.commonservice.popupStore.service.PopupStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/popupStore")
@RequiredArgsConstructor
public class PopupStoreController {
    private final PopupStoreService popupStoreService;

    @GetMapping("/list")
    public String list(Model model) {
//        List<PopupStore> list = popupStoreService.findAll();
//        model.addAttribute("list", list);
        return "popupStore/list";
    }

    @GetMapping("/detail")
    public String detail(Model model) {
//        PopupStore popupStore = popupStoreService.findById();
//        model.addAttribute("popupStore", popupStore);
        return "popupStore/detail";
    }
}

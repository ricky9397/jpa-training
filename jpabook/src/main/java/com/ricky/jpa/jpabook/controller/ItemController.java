package com.ricky.jpa.jpabook.controller;


import com.ricky.jpa.jpabook.domain.item.Book;
import com.ricky.jpa.jpabook.domain.item.Item;
import com.ricky.jpa.jpabook.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemservice;

    @GetMapping("/items/new")
    public String createForm() {
        return "/html/items/createItemForm.html";
    }

    @PostMapping("/items/new")
    public String create(Book item) {
        itemservice.saveItem(item);
        return "redirect:/items";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemservice.findOne(itemId);
        model.addAttribute("item", item);
        return "/html/items/updateItemForm";
    }

    /**
     * 상품 수정
     */
    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("item") Book item) {
        itemservice.saveItem(item);
        return "redirect:/items";
    }

    /**
     * 상품 목록
     */
    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemservice.findItems();
        model.addAttribute("items", items);
        return "/html/items/itemList";
    }


}

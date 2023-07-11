package com.ricky.jpa.jpabook.controller;

import com.ricky.jpa.jpabook.domain.Member;
import com.ricky.jpa.jpabook.domain.Order;
import com.ricky.jpa.jpabook.domain.OrderSearch;
import com.ricky.jpa.jpabook.domain.item.Item;
import com.ricky.jpa.jpabook.service.ItemService;
import com.ricky.jpa.jpabook.service.MemberService;
import com.ricky.jpa.jpabook.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "/html/order/orderForm.html";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "/html/order/orderList.html";
    }

    @RequestMapping(value = "/orders/{orderId}/cancel")
    @GetMapping("/orders/{orderId}/cancel")
    public String processCancelBuy(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}

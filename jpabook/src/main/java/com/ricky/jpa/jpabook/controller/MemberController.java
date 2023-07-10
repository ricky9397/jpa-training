package com.ricky.jpa.jpabook.controller;

import com.ricky.jpa.jpabook.domain.Address;
import com.ricky.jpa.jpabook.domain.Member;
import com.ricky.jpa.jpabook.service.ItemService;
import com.ricky.jpa.jpabook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/members/new")
    public String createForm() {
        return "/html/members/createMemberForm.html";
    }

    @PostMapping("/members/new")
    public String create(Member member, String city, String street, String zipcode) {
        Address address = new Address(city, street, zipcode);
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/html/members/memberList.html";
    }



}

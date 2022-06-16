package com.example.springcache.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcache.domain.Member;
import com.example.springcache.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/members")
	public List<Member> getMembers() {
		return memberService.getMembers();
	}

	@GetMapping("/members/{id}")
	public Member getMember(@PathVariable("id") Long id) {
		return memberService.getMember(id);
	}

	@PatchMapping("/members/{id}")
	public Member addMemberAge(@PathVariable("id") Long id) {
		return memberService.addMemberAge(id);
	}
}

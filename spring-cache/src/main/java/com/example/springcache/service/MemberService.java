package com.example.springcache.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springcache.domain.Member;
import com.example.springcache.domain.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final ObjectProvider<MemberService> provider;

	@Cacheable(cacheNames = "memberList")
	public List<Member> getMembers() {
		log.info("MemberService.getMembers invoke");
		return memberRepository.findAll();
	}

	@Transactional
	@Cacheable(cacheNames = "member", key = "#id")
	public Member getMember(Long id) {
		log.info("MemberService.getMember(id = {}) invoke", id);
		return memberRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다. id = " + id));
	}

	@Scheduled(fixedDelay = 5000)
	public void addAge() {
		log.info("MemberService.addAge");
		MemberService innerService = provider.getIfAvailable();
		innerService.addMemberAge(1l);
	}

	@Transactional
	@CachePut(cacheNames = "member", key = "#id")
	public Member addMemberAge(Long id) {
		log.info("MemberService.addMemberAge");
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("멤버가 존재하지 않습니다. id = " + id));

		member.addAge();
		return member;
	}
}

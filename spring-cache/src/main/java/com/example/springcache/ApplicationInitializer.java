package com.example.springcache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.springcache.domain.Member;
import com.example.springcache.domain.MemberRepository;
import com.example.springcache.domain.Team;
import com.example.springcache.domain.TeamRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApplicationInitializer implements ApplicationRunner {

	private final TeamRepository teamRepository;
	private final MemberRepository memberRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Team team = new Team("team");
		List<Member> memberList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Member member = Member.builder()
				.address("address" + i)
				.age(0)
				.name("name" + i)
				.team(team)
				.build();
			memberList.add(member);
		}
		teamRepository.save(team);
		memberRepository.saveAll(memberList);
	}
}

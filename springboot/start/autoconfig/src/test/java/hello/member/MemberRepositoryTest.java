package hello.member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	private MemberRepository sut;

	private Member member;

	@BeforeEach
	void setup() {
		sut.initTable();
		member = new Member("memberId", "name");
		sut.save(member);
	}

	@Transactional
	@Test
	void testFind() {
		Member result = sut.find("memberId");

		assertThat(result.getMemberId()).isEqualTo(member.getMemberId());
		assertThat(result.getName()).isEqualTo(member.getName());
	}
}
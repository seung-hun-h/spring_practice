package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceV1Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberRepositoryV1 memberRepository;
    private MemberServiceV1 memberService;

    @BeforeEach
    void setUp() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        memberRepository = new MemberRepositoryV1(dataSource);
        memberService = new MemberServiceV1(memberRepository);
    }

    @AfterEach
    void tearDown() throws SQLException {
        memberRepository.deleteMember(MEMBER_A);
        memberRepository.deleteMember(MEMBER_B);
        memberRepository.deleteMember(MEMBER_EX);
    }

    @Test
    @DisplayName("계좌 이체 성공")
    void accountTransfer() throws SQLException {
        // given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        // when
        int amount = 2000;
        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), amount);

        // then
        Member findMemberA = memberRepository.findMember(memberA.getMemberId());
        Member findMemberB = memberRepository.findMember(memberB.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(memberA.getMoney() - amount);
        assertThat(findMemberB.getMoney()).isEqualTo(memberB.getMoney() + amount);
    }

    @Test
    @DisplayName("계좌 이체 실패")
    void accountTransferEx() throws SQLException {
        // given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        // when
        int amount = 2000;
        assertThatThrownBy(() -> memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), amount))
                .isInstanceOf(IllegalStateException.class);

        // then
        Member findMemberA = memberRepository.findMember(memberA.getMemberId());
        Member findMemberB = memberRepository.findMember(memberB.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(memberA.getMoney() - amount);
        assertThat(findMemberB.getMoney()).isEqualTo(memberB.getMoney());
    }
}
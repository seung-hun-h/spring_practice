package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repogitory.MemoryMemberRepogitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepogitory memberRepogitory;

    /**
     * MemberService memberService = new MemberService();
     * MemoryMemberRepogitory = new MemoryMemberRepogitory();
     * 는 서로 다른 인스턴스이기 때문에 문제가 발생할 수도 있다.
     * 현재는 MemoryMemberRepogitory의 store가 static이기 때문에 문제가 없지만
     * static 키워드가 없을 경우 문제 발생할 수 있다.
     */

    @BeforeEach
    public void beforeEach() {
        memberRepogitory = new MemoryMemberRepogitory();
        memberService = new MemberService(memberRepogitory);
    }

    @AfterEach // 메서드 실행 끝날 때마다 실행 되는  콜백 메서드
    public void afterEach() {
        memberRepogitory.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("Spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicatedMemberException() {
       //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

       //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try {
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

        */
       //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
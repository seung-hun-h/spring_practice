package hello.hellospring.repogitory;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepogitoryTest {

    MemoryMemberRepogitory repogitory = new MemoryMemberRepogitory();

    @AfterEach // 메서드 실행 끝날 때마다 실행 되는  콜백 메서드
    public void afterEach() {
        repogitory.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repogitory.save(member);

        Member result = repogitory.findById(member.getId()).get();
//        Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repogitory.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repogitory.save(member2);

        Member result = repogitory.findByName(member1.getName()).get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repogitory.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repogitory.save(member2);

        List<Member> result = repogitory.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

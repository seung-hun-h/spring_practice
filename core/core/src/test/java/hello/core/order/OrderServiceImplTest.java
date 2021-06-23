package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    /**
     * 수정자 주입을 사용하면, 클래스를 직접 까봐서
     * 어떤 인스턴스를 주입받고 있는 지 확인하고
     * 하나씩 setXxx 메서드르 호출하여 인스턴스 주입을 해야한다.
     *
     * 생성자 주입은 인스턴스를 생성할때 의존성을 주입하기 때문에 하나씩 확인할 필요가 없다.
     * */
}

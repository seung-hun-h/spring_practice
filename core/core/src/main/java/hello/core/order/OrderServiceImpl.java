package hello.core.order;

import hello.core.discount.DistcountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DistcountPolicy distcountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DistcountPolicy distcountPolicy) {
        this.memberRepository = memberRepository;
        this.distcountPolicy = distcountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = distcountPolicy.discountPrice(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

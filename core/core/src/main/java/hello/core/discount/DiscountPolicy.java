package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    int discountPrice(Member member, int price);
}

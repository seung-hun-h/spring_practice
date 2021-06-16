package hello.core.discount;

import hello.core.member.Member;

public interface DistcountPolicy {

    int discountPrice(Member member, int price);
}

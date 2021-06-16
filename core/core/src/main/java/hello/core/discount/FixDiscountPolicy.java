package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DistcountPolicy {

    private int discountFixAmount = 1000;
    @Override
    public int discountPrice(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return 1000;
        }
        return 0;
    }
}

package jpabook.jpashop.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    MEMBER("ROLE_MEMBER", "회원"),
    SELLER("ROLE_SELLER", "판매자");

    private final String key;
    private final String title;
}

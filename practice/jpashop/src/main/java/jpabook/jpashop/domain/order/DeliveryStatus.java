package jpabook.jpashop.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DeliveryStatus {

    READY("DELIVERY_READY", "배송 준비"),
    COMP("DELIVERY_COMP", "배송 완료");

    private final String key;
    private final String title;
}

package jpabook.jpashop.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    ORDER("ORDER_STATUS_ORDER", "주문 완료"),
    CANCEL("ORDER_STATUS_CANCEL", "주문 취소");

    private final String key;
    private final String title;
}

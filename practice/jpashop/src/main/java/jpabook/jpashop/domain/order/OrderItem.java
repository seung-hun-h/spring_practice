package jpabook.jpashop.domain.order;

import jpabook.jpashop.domain.item.Item;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    protected OrderItem(Item item, int orderPrice, int count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        item.removeStock(count);
        OrderItem orderItem = new OrderItem(item, orderPrice, count);

        return orderItem;
    }

    public void addOrderInfo(Order order) {
        this.order = order;
    }

    public void cancel() { // 주문취소
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }
}

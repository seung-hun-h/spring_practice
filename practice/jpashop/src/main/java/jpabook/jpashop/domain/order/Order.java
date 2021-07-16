package jpabook.jpashop.domain.order;

import jpabook.jpashop.domain.BaseTimeEntity;
import jpabook.jpashop.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "orders")
@Entity
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; // 배송 정보

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static Order createOrder(User user, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order(user, delivery, OrderStatus.ORDER);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.addOrderInfo(this);
    }

    protected Order(User user, Delivery delivery, OrderStatus orderStatus) {
        this.user = user;
        this.delivery = delivery;
        this.orderStatus = orderStatus;
    }

    public void update(Delivery delivery, OrderStatus orderStatus) {
        this.delivery = delivery;
        this.orderStatus = orderStatus;
    }

    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송 완료된 주문은 취소할 수 없습니다");
        }

        orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice() {
        int totalPrice = orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
        return totalPrice;
    }
}

package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.order.Delivery;
import jpabook.jpashop.domain.order.DeliveryStatus;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderItem;
import jpabook.jpashop.domain.repository.ItemRepository;
import jpabook.jpashop.domain.repository.OrderRepository;
import jpabook.jpashop.domain.repository.UserRepository;
import jpabook.jpashop.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long userId, Long itemId, int count) {

        // 엔티티 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + userId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id = " + itemId));


        // 배송정보 생성
        Delivery delivery = Delivery.builder()
                .address(user.getAddress())
                .status(DeliveryStatus.READY)
                .build();

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        
        // 주문 생성
        Order order = Order.createOrder(user, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /***
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {

        // 주문 엔티티 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. id = " + orderId));
        
        // 주문 취소
        order.cancel();
    }

}

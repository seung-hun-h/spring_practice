package jpabook.jpashop.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.order.*;
import jpabook.jpashop.domain.repository.OrderRepository;
import jpabook.jpashop.domain.user.Address;
import jpabook.jpashop.domain.user.Role;
import jpabook.jpashop.domain.user.User;
import jpabook.jpashop.exception.NotEnoughStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void init() {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void 주문() {

        //given
        User user = createUser("abc@naver.com");
        Book book = createBook();

        Long orderId = orderService.order(user.getId(), book.getId(), 10);
        //when
        Order order = orderRepository.findById(orderId).get();

        //then
        assertThat(orderId).isEqualTo(order.getId());
        assertThat(OrderStatus.ORDER).isEqualTo(order.getOrderStatus());
        assertThat(1).isEqualTo(order.getOrderItems().size());
        assertThat(90).isEqualTo(book.getStockQuantity());
        assertThat(10000 * 10).isEqualTo(order.getTotalPrice());
    }

    @Test
    public void 상품_주문_수량_초과() {

        //given
        User user = createUser("abc@naver.com");
        Book book = createBook();

        //then
        assertThrows(NotEnoughStockException.class, () ->
                orderService.order(user.getId(), book.getId(), 10000)    //when
        );
    }

    @Test
    public void 주문_취소() {

        //given
        User user = createUser("abc@naver.com");
        Book book = createBook();

        Long orderId = orderService.order(user.getId(), book.getId(), 10);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order order = orderRepository.findById(orderId).get();
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(book.getStockQuantity()).isEqualTo(100);
    }

    @Test
    public void 주문_조회() {

        //given
        User user1 = createUser("abc@naver.com");
        Book book = createBook();

        Long orderId1 = orderService.order(user1.getId(), book.getId(), 10);
        Long orderId2 = orderService.order(user1.getId(), book.getId(), 10);
        //when
        List<Order> orders = orderService.findOrders(new OrderSearch(user1.getName(), OrderStatus.ORDER));
        //then
        assertThat(orders.size()).isEqualTo(2);
    }

    private User createUser(String email) {
        User user = User.builder()
                .address(new Address("city", "street", "zipcode"))
                .name("user1")
                .role(Role.GUEST)
                .email(email)
                .build();
        em.persist(user);
        return user;
    }

    private Book createBook() {
        Book book = Book.builder()
                .author("kim")
                .isbn("abc")
                .name("jpa")
                .price(10000)
                .stockQuantity(100)
                .build();
        em.persist(book);
        return book;
    }

}
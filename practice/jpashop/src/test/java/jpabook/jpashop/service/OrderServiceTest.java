package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품_주문() {

        //given
        Member member = createMember("member1", new Address("city", "street", "zipcode"));
        Item item = createBook("JPA", 10, 10000);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order order = orderRepository.findOne(orderId);

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(order.getOrderItems().size()).isEqualTo(1);
        assertThat(order.getTotalPrice()).isEqualTo(10000 * orderCount);
        assertThat(item.getStockQuantity()).isEqualTo(8);
    }
    
    @Test
    public void 상품_주문_재고_초과() {
        
        //given
        Member member = createMember("member1", new Address("city", "street", "zipcode"));
        Item item = createBook("JPA", 10, 10000);
        
        int orderCount = 11;
        //when

        //then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount);
        });
    }

    @Test
    public void 주문취소() {
        
        //given
        Member member = createMember("member1", new Address("city", "street", "zipcode"));
        Item item = createBook("JPA", 10, 10000);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when

        orderService.cancel(orderId);

        //then
        Order order = orderRepository.findOne(orderId);

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);

    }
    
    
    private Member createMember(String name, Address address) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(address);
        em.persist(member);

        return member;
    }

    private Book createBook(String name, int stockQuantity, int price) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }

}
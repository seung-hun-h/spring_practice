package jpabook.jpashop.domain.repository;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderSearch;
import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.domain.order.QOrder;
import jpabook.jpashop.domain.user.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final JPAQueryFactory query;
    private final EntityManager em;

    public List<Order> findAll(OrderSearch orderSearch) {
        QOrder order = QOrder.order;
        QUser user = QUser.user;
        return query
                .select(order)
                .from(order)
                .join(order.user, user)
                .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getUserName()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression nameLike(String nameCond) {
        if (!StringUtils.hasText(nameCond)) {
            return null;
        }
        return QUser.user.name.like(nameCond);
    }


    private BooleanExpression statusEq(OrderStatus statusCond) {
        if (statusCond == null) {
            return null;
        }

        return QOrder.order.orderStatus.eq(statusCond);
    }

    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    public Optional<Order> findById(Long orderId) {
        QOrder order = QOrder.order;
        return Optional.ofNullable(query
                .selectFrom(order)
                .where(order.id.eq(orderId))
                .fetchOne());
    }


}

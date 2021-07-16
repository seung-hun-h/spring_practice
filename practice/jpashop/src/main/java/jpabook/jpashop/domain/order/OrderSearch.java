package jpabook.jpashop.domain.order;

public class OrderSearch {

    private String userName;
    private OrderStatus orderStatus;

    public OrderSearch() {
    }

    public OrderSearch(String userName) {
        this.userName = userName;
    }

    public OrderSearch(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderSearch(String userName, OrderStatus orderStatus) {
        this.userName = userName;
        this.orderStatus = orderStatus;
    }
}

package jpabook.jpashop.domain.order;

import jpabook.jpashop.domain.user.Address;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    public Delivery(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }

    public void update(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }
}


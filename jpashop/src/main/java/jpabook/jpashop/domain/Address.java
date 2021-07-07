package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@AllArgsConstructor
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }
}

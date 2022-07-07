package hello.datajdbc.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

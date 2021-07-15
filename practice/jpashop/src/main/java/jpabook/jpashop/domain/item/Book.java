package jpabook.jpashop.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@AllArgsConstructor
@DiscriminatorValue("B")
@Entity
public class Book extends Item{

    private String author;
    private String isbn;

    protected Book() {
    }

}

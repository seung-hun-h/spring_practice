package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.NotEnoughStockException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    
    @Test
    public void 상품_저장() {
        
        //given
        Book book = new Book();
        book.setName("Book1");
        //when
        Long saveId = itemService.saveItem(book);

        //then
        assertThat(saveId).isEqualTo(book.getId());
    }

    @Test
    public void 상품_재고_부족() {

        //given
        Book book = new Book();
        book.setName("book1");

        //when
        book.setStockQuantity(10);

        //then
        assertThrows(NotEnoughStockException.class, () -> {
            book.removeStock(11);
        });
    }
}
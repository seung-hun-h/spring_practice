package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    public void 상품_저장() {

        //given
        Item item = new Book("jpa", 10000, 100, "kim", "abc");

        //when
        Long saveId = itemService.save(item);

        //then
        assertThat(saveId).isEqualTo(item.getId());
    }

    @Test
    public void 상품_조회() {

        //given
        Item item = new Book("jpa", 10000, 100, "kim", "abc");
        itemService.save(item);

        //when
        Item findItem = itemService.findItem(item.getId());

        //then
        assertThat(item.getName()).isEqualTo(findItem.getName());

    }
}
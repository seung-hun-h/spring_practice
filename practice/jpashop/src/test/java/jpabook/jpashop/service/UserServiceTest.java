package jpabook.jpashop.service;

import jpabook.jpashop.domain.user.Address;
import jpabook.jpashop.domain.user.Role;
import jpabook.jpashop.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void 회원_가입() {
        //given
        Address address = new Address("zipcode", "city", "stree");
        User user = User.builder().role(Role.GUEST)
                .name("name")
                .email("email")
                .address(address)
                .build();
        //when
        Long saveId = userService.save(user);
        //then
        assertThat(saveId).isEqualTo(user.getId());
    }

    @Test
    public void 회원_가입_중복_이메일_불가() {

        //given
        Address address = new Address("zipcode", "city", "stree");
        User user1 = User.builder().role(Role.GUEST)
                .name("name")
                .email("email")
                .address(address)
                .build();
        User user2 = User.builder().role(Role.GUEST)
                .name("name")
                .email("email")
                .address(address)
                .build();
        //when
        userService.save(user1);
        //then
        Assertions.assertThrows(IllegalStateException.class, () -> {
            userService.save(user1);
        });
    }
    @Test
    public void 회원_조회() {
        //given
        Address address = new Address("zipcode", "city", "stree");
        User user = User.builder().role(Role.GUEST)
                .name("name")
                .email("email")
                .address(address)
                .build();
        Long userId = userService.save(user);
        //when
        User findUser = userService.findUser(userId);
        //then
        assertThat(findUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void 회원_전체_조회() {
        //given
        Address address = new Address("zipcode", "city", "stree");
        User user1 = User.builder().role(Role.GUEST)
                .name("name")
                .email("email")
                .address(address)
                .build();
        //given
        User user2 = User.builder().role(Role.GUEST)
                .name("name")
                .email("email2")
                .address(address)
                .build();

        userService.save(user1);
        userService.save(user2);
        //when
        List<User> users = userService.findAll();

        //then
        assertThat(users.size()).isEqualTo(2);
    }
}
package jpabook.jpashop.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles(value = "dev")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 사용자_저장() {

        Address address = new Address("zipcode", "city", "stree");
        User user = User.builder().role(Role.GUEST)
                .name("name")
                .email("email")
                .address(address)
                .build();
        //when
        User saveUser = userRepository.save(user);

        //then
        assertThat(saveUser.getEmail()).isEqualTo(user.getEmail());
    }
}
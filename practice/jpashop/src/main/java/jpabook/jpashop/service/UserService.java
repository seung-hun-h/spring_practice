package jpabook.jpashop.service;

import jpabook.jpashop.domain.user.Address;
import jpabook.jpashop.domain.user.User;
import jpabook.jpashop.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(User user) {
        vaildDuplicateUser(user);
        return userRepository.save(user).getId();
    }

    @Transactional
    public Long update(Long userId, String name, String picture, Address address) {
        User user = userRepository.findById(userId)
                        .orElseThrow(() ->
                            new IllegalArgumentException("해당 사용자가 없습니다. id = " + userId));

        user.update(name, picture, address);
        return userId;
    }

    public User findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 사용자가 없습니다. id = " + userId));

        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }

    private void vaildDuplicateUser(User user) {
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        if (findUser.isPresent()) {
            throw new IllegalStateException("해당 사용자가 이미 존재합니다");
        }
    }

}

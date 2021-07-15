package jpabook.jpashop.domain.user;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Column(nullable = false)
    @Embedded
    private Address address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Address address, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.address = address;
        this.role = role;
    }

    public User update(String name, String picture, Address address) {
        this.name = name;
        this.picture = picture;
        this.address = address;
        
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

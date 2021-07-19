package jpabook.jpashop.domain.web.dto;

import jpabook.jpashop.domain.user.Address;
import jpabook.jpashop.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserForm {

    @NotEmpty(message = "올바른 이름을 입력하세요")
    private String name;

    @NotEmpty(message = "올바른 형식의 이메일을 입력하세요")
    private String email;

    private String picture;

    @NotEmpty(message = "도시는 필수 입니다")
    private String city;

    @NotEmpty(message = "거리는 필수 입니다")
    private String street;

    @NotEmpty(message = "우편번호는 필수 입니다")
    private String zipcode;

    public UserForm() {
    }

    @Builder
    public UserForm(String name, String email, String picture, String city, String street, String zipcode, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

package com.seunghun.springboot.config.auth.dto;

import com.seunghun.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
// 인증된 사용자 정보만 필요, name, email, picture 외 정보 저장X
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

package com.seunghun.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //mvc를 통해 '/hello'로 GET 요청 보낸다.
                .andExpect(status().isOk()) // perform의 결과 검증
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴_된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

        /**
         * param: API 테스트할 때 사용될 요청 파라미터 설정, String만 허용
         * jsonPath
         *  - JSON 응답값을 필드별로 검증할 수 있는 메소드
         *  - $를 기준으로 필드명을 명시.
         * */
    }

}
/**
 * @ExtendWith(SpringExtension.class):
 *  - 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
 *  - 여기서는 SpringExtension을 실행시킨다.
 *  - 즉 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
 * @WebMvcTest
 *  - 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
 *  - 선언할 경우, @Controller, @ControllerAdvice 등을 사용할 수 있다.
 *  - 단, @Service, @Component, @Repogitory 등은 사용할 수 없다.
 *  - 여기서는 컨트롤러만 사용하기 때문에 선언한다.
 * @Autowired
 *  - 스프링이 관리하는 빈(Bean)을 주입받는다.
 * private MockMvc mvc
 *  - 웹 API 테스트할 때 사용
 *  - 스프링 MVC 테스트의 시작
 *  - HTTP GET, POST 등에 대한 API 테스트 가능
 *
 * */

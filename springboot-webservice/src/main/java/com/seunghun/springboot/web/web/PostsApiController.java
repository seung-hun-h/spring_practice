package com.seunghun.springboot.web.web;

import com.seunghun.springboot.web.service.PostsService;
import com.seunghun.springboot.web.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

}
/**
 * 스프링에서 Bean을 주입하는 방식
 *  - @Autowired
 *  - setter
 *  - Constructor
 *  이 중 가장 권장하는 방식은 생성자로 Bean 객체를 받는 것.
 * @RequiredArgsConstructor를 사용하면 final이 선언된 모든 필드를 인자 값으로하는
 * 생성자를 롬복의 해당 어노테이션이 대신 생성해준 것.
 * 해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속 수정해야하는 번거로움을 덜어준다.
 * */
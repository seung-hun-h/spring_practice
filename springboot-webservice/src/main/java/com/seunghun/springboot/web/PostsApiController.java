package com.seunghun.springboot.web;

import com.seunghun.springboot.web.dto.PostsSaveRequestDto;
import com.seunghun.springboot.service.PostsService;
import com.seunghun.springboot.web.dto.PostsResponseDto;
import com.seunghun.springboot.web.dto.PostsUpdateRequestDto;
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

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
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
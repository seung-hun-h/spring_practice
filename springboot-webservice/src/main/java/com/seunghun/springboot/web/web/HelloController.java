package com.seunghun.springboot.web.web;

import com.seunghun.springboot.web.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @RestController: 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어준다.
 * @GepMapping: HTTP Get 요청을 받을 수 있는 API를 만들어준다.
 * */


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hell() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    /**
     * @RequestParam: 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션.
     * 여기서는 외부에서 name 이란 이름으로 넘긴 파라미터를 메소드 파라미터에 저장한다.
     * */
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}

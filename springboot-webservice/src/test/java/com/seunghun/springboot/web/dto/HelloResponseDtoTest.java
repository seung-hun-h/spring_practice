package com.seunghun.springboot.web.dto;

import com.seunghun.springboot.web.web.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        /**
         * assertThat: assertj라는 검증 테스트 라이브러리의 검증 메소드
         * isEqualsTo: assertj의 동등 비교 메소드
         * */
    }
    /**
     * JUnit 기본 assertThat VS assertj assertThat
     *  - 추가 라이브러리가 필요 없다.
     *  - 자동 완성이 조금 더 확실히 지원된다.
     * */
}

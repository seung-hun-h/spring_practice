package com.seunghun.springboot.web.web.dto;

import com.seunghun.springboot.web.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
/**
 * Controller와 Service에서 사용할 Dto
 * Entity 클래스(Posts)와 유사하다.
 * 하지만 Entity 클래스는 수 많은 서비스 클래스나 로직의 동작 기준이기 때문에
 * 자주 변경해서는 안된다.
 * 반면, Request나 Response 용 Dto는 View를 위한 클래스라 자주 변경이 필요하다.
 * 따라서 ViewLayer와 DBLayer는 철저히 분리해야 한다.
 * */

package com.seunghun.springboot.service;

import com.seunghun.springboot.domain.posts.Posts;
import com.seunghun.springboot.domain.posts.PostsRepository;
import com.seunghun.springboot.web.dto.PostsListResponseDto;
import com.seunghun.springboot.web.dto.PostsResponseDto;
import com.seunghun.springboot.web.dto.PostsSaveRequestDto;
import com.seunghun.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        postsRepository.delete(posts);

    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
/**
 * JPA 영속성 컨텍스트
 *  - 엔티티를 영구 저장하는 환경
 *  - JPA의 엔티티 매니저가 활성된 상태로 트랜잭션안에서 데이터베이스의 데이터를 가져오면
 *      이 데이터는 영속성 컨텍스트가 유지된 상태
 *  - 이 상태에서 해당 데이터의 값을 변경하면, 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영.
 *  - 따라서 update 메소드에서 쿼리를 날릴 필요가 없다.
 *  - 이를 더티 체킹이라한다.
 * */

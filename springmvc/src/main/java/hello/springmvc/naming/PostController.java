package hello.springmvc.naming;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @GetMapping("/posts")
    public Post fetchPosts() {
        return Post.builder()
                .id(1L)
                .title("This is post")
                .content("This is content")
                .userId(5L)
                .accessCounts(123123L)
                .build();
    }
}

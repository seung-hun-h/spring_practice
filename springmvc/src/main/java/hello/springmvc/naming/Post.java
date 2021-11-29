package hello.springmvc.naming;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {

    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;
    private final Long accessCounts;

    @Builder
    public Post(Long id, String title, String content, Long userId, Long accessCounts) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.accessCounts = accessCounts;
    }
}

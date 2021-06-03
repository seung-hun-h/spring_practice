package com.seunghun.springboot.web.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타낸다.
public class Posts {

    @Id // 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙, auto increment
    private Long id;

    // @Column 어노테이션을 명시하지 않아도 해당 클래스의 필드는 모두 칼럼이 된다.
    // 변경 사항이 있을 경우 명시한다. ex) varchar(255) -> varchar(500)
    @Column(length = 500, nullable = false) //String = varchar
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // varchar -> text
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    /***
     * Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
     * 무작정 getter/setter를 생성하면 어디서 인스턴스 값이 변해야하는 지 명확하게 구분할 수 없기 때문
     * 따라서, 필드 값 변경이 필요하면 목적과 의도를 알 수 있는 메소드를 추가해야한다.
     *
     * Setter가 없는 상황에서 필드의 값을 채워 DB에 삽입하기 위해서
     * 생성자를 통해 최종 값을 채운 후 DB에 삽입하거나,
     * 빌더 클래스를 사용한다.
     *
     */
}

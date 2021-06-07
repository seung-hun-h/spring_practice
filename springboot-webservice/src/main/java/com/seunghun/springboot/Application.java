package com.seunghun.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @SpringBootApplicationi 어노테이션
 * 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
 * 해당 어노테이션이 있는 위치부터 읽어 나가기 때문에, 메인 클래스는 항상 프로젝트 최상단에 위치해야함.
 * */

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /**
          * SpringApplication.run(): WAS 실행
          * 언제 어디서나 같은 환경에서 스프링 부트를 배포하기 위해 내장 WAS 사용 권장
        * */
        SpringApplication.run(Application.class, args);
    }
}

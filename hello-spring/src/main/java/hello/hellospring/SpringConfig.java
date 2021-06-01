package hello.hellospring;

import hello.hellospring.repogitory.MemberRepogitory;
import hello.hellospring.repogitory.MemoryMemberRepogitory;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepogitory());
    }

    @Bean
    public MemberRepogitory memberRepogitory() {
        return new MemoryMemberRepogitory();
    }
}

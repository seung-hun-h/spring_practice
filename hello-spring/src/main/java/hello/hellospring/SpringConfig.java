package hello.hellospring;

import hello.hellospring.repogitory.JdbcTemplateMemberRepogitory;
import hello.hellospring.repogitory.MemberRepogitory;
import hello.hellospring.repogitory.MemoryMemberRepogitory;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepogitory());
    }

    @Bean
    public MemberRepogitory memberRepogitory() {
//        return new MemoryMemberRepogitory();
        return new JdbcTemplateMemberRepogitory(dataSource);
    }

}

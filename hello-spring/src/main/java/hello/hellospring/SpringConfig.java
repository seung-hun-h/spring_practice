package hello.hellospring;

import hello.hellospring.repogitory.JpaMemberRepogitory;
import hello.hellospring.repogitory.MemberRepogitory;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepogitory());
    }

    @Bean
    public MemberRepogitory memberRepogitory() {
//        return new MemoryMemberRepogitory();
//        return new JdbcTemplateMemberRepogitory(dataSource);
        return new JpaMemberRepogitory(em);
    }


}

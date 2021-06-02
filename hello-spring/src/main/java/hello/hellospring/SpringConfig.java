package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repogitory.MemberRepogitory;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepogitory memberRepogitory;

    @Autowired
    public SpringConfig(MemberRepogitory memberRepogitory) {
        this.memberRepogitory = memberRepogitory;
    }

//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepogitory());
//    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepogitory);
    }

//    @Bean
//    public MemberRepogitory memberRepogitory() {
//        return new MemoryMemberRepogitory();
//        return new JdbcTemplateMemberRepogitory(dataSource);
//        return new JpaMemberRepogitory(em);

//    }



}

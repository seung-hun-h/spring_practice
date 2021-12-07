package hello.aop.pointcut;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
public class ThisTargetTest {

  @Autowired
  MemberService memberService;

  @Test
  public void success() throws Exception {
    //given
    log.info("memberService Proxy={}", memberService.getClass());
    memberService.hello("helloA");
  }

  @Slf4j
  @Aspect
  static class ThisTargetAspect {

    @Around("this(hello.aop.member.MemberService)")
    public Object doThisAspect(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[this-interface] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }
    @Around("target(hello.aop.member.MemberService)")
    public Object doTargetAspect(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[target-interface] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }
    @Around("this(hello.aop.member.MemberServiceImpl)")
    public Object doImplThisAspect(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[this-impl] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }
    @Around("target(hello.aop.member.MemberService)")
    public Object doImplTargetAspect(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[target-impl] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }
  }
}

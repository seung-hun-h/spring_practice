package hello.aop.proxyvs;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {
  
  @Test 
  public void jdkProxy() throws Exception {
    MemberServiceImpl target = new MemberServiceImpl();

    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(false);
    Object proxy = proxyFactory.getProxy();

    // JDK 동적 프록시 - 인터페이스 타입 변환 성공
    MemberService memberServiceProxy = (MemberService) proxy;

    // JDK 동적 프록시 - 구체 클래스 타입 변환 실패
    assertThatThrownBy(() -> {
      MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) proxy;
    })
      .isInstanceOf(ClassCastException.class);
  }
  @Test
  public void cglibProxy() throws Exception {
    MemberServiceImpl target = new MemberServiceImpl();

    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true);

    Object proxy = proxyFactory.getProxy();

    // cglib 동적 프록시 - 인터페이스 타입 변환 성공
    MemberService memberServiceProxy = (MemberService) proxy;

    // cglib 동적 프록시 - 구체 클래스 타입 변환 성공
    MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) proxy;
  }

  
}

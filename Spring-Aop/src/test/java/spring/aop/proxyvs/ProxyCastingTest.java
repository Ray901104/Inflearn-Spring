package spring.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import spring.aop.member.MemberService;
import spring.aop.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); //JDK 동적 프록시

        //JDK 동적 프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //JDK 동적 프록시를 구체클래스로 캐스팅 실패
        assertThatThrownBy(() -> {
                    MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
                }).isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); //CGLIB 동적 프록시

        //CGLIB 동적 프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        log.info("proxy class={}", memberServiceProxy.getClass());

        //CGLIB 동적 프록시를 구체클래스로 캐스팅 성공
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    }
}

package spring.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.aop.member.MemberService;

/**
 * application.properties
 * spring.aop.proxy-target-class=true -> CGLIB (Default)
 * spring.aop.proxy-target-class=false -> JDK 동적 프록시 or CGLIB
 */
@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect {

        //부모 타입 허용
        @Around("this(spring.aop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        //부모 타입 허용
        @Around("target(spring.aop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        //부모 타입 허용
        @Around("this(spring.aop.member.MemberServiceImpl)")
        public Object doThisClass(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-class] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        //부모 타입 허용
        @Around("target(spring.aop.member.MemberServiceImpl)")
        public Object doTargetClass(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-class] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}

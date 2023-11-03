package springCore.basic.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import springCore.basic.member.Member;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 자동 주입할 스프링 빈이 없으면 수정자 메서드 자체가 호출이 안된다.
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("member = " + member);
        }

        // 자동 주입할 스프링 빈이 없으면 null 을 주입한다.
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("member = " + member);
        }

        // 자동 주입할 스프링 빈이 없으면 Optional.empty 를 주입한다.
        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("member = " + member);
        }
    }
}

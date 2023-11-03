package springCore.basic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "springCore.basic", // default : AutoAppConfig의 패키지 하위를 모두 스캔한다. 보통 프로젝트의 시작 위치에 지정한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동 등록 빈 vs 자동 등록 빈 -> 수동 등록 빈이 우선권을 가진다.
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}

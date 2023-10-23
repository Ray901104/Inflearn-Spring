package basic.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 이걸 사용하면 따로 log를 정의해주지 않아도 된다!
@RestController
public class LogTestController {

//    private final Logger logger = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        /**
         * 올바른 로그 사용법
         * log.debug("data="+data)
         * 로그 출력 레벨을 info로 설정해도 해당 코드에 있는 "data="+data가 실제 실행이 되어 버린다.
         * 결과적으로 문자 더하기 연산이 발생한다.
         * log.debug("data={}", data)
         * 로그 출력 레벨을 info로 설정하면 아무일도 발생하지 않는다. 따라서 앞과 같은 의미없는 연산이
         * 발생하지 않는다.
         */
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}

package spring.advanced.templatecallbackstrategy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.advanced.templatecallbackstrategy.trace.logtrace.LogTrace;
import spring.advanced.templatecallbackstrategy.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        //return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}

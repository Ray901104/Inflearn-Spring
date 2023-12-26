package spring.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    private final ObjectProvider<CallServiceV2> objectProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> objectProvider) {
        this.objectProvider = objectProvider;
    }

    public void external() {
        log.info("call external");
        CallServiceV2 callServiceV2 = objectProvider.getObject(); //지연 조회
        callServiceV2.internal();
    }

    public void internal() {
        log.info("call internal");
    }
}

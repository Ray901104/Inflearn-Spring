package spring.advanced.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import spring.advanced.proxy.app.v1.OrderRepositoryV1;
import spring.advanced.proxy.trace.TraceStatus;
import spring.advanced.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1 target;
    private final LogTrace trace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.save()");

            //target 호출
            target.save(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}

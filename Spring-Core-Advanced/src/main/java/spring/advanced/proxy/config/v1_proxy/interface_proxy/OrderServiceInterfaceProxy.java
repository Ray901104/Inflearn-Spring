package spring.advanced.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import spring.advanced.proxy.app.v1.OrderServiceV1;
import spring.advanced.proxy.trace.TraceStatus;
import spring.advanced.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {

    private final OrderServiceV1 target;
    private final LogTrace trace;

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderService.orderItem()");

            //target 호출
            target.orderItem(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}

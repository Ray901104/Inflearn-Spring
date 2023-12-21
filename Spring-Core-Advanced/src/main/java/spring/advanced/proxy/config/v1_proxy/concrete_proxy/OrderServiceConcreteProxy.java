package spring.advanced.proxy.config.v1_proxy.concrete_proxy;

import spring.advanced.proxy.app.v2.OrderServiceV2;
import spring.advanced.proxy.trace.TraceStatus;
import spring.advanced.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 target;
    private final LogTrace trace;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

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

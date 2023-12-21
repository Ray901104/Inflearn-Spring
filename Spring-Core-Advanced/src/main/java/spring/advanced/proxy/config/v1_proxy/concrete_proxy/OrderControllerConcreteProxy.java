package spring.advanced.proxy.config.v1_proxy.concrete_proxy;

import org.springframework.web.bind.annotation.RequestParam;
import spring.advanced.proxy.app.v2.OrderControllerV2;
import spring.advanced.proxy.trace.TraceStatus;
import spring.advanced.proxy.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    private final OrderControllerV2 target;
    private final LogTrace trace;

    public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(@RequestParam("itemId") String itemId) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");

            //target 호출
            String result = target.request(itemId);

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}

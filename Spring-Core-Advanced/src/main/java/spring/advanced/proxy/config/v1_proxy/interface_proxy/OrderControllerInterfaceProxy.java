package spring.advanced.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import spring.advanced.proxy.app.v1.OrderControllerV1;
import spring.advanced.proxy.trace.TraceStatus;
import spring.advanced.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 target;
    private final LogTrace trace;

    @Override
    public String request(String itemId) {
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

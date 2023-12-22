package spring.advanced.templatecallbackstrategy.app.v5;

import org.springframework.stereotype.Service;
import spring.advanced.templatecallbackstrategy.trace.callback.TraceCallback;
import spring.advanced.templatecallbackstrategy.trace.callback.TraceTemplate;
import spring.advanced.templatecallbackstrategy.trace.logtrace.LogTrace;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", (TraceCallback<Void>) () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}

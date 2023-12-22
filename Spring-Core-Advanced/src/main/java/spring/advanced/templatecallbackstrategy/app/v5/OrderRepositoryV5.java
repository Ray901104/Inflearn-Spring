package spring.advanced.templatecallbackstrategy.app.v5;

import org.springframework.stereotype.Repository;
import spring.advanced.templatecallbackstrategy.trace.callback.TraceCallback;
import spring.advanced.templatecallbackstrategy.trace.callback.TraceTemplate;
import spring.advanced.templatecallbackstrategy.trace.logtrace.LogTrace;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId) {
        template.execute("OrderRepository.save()", (TraceCallback<Void>) () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

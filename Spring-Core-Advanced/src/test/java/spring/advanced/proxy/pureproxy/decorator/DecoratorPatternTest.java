package spring.advanced.proxy.pureproxy.decorator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.proxy.pureproxy.decorator.code.Component;
import spring.advanced.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import spring.advanced.proxy.pureproxy.decorator.code.MessageDecorator;
import spring.advanced.proxy.pureproxy.decorator.code.RealComponent;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }
}

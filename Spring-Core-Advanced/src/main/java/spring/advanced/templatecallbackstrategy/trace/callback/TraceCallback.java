package spring.advanced.templatecallbackstrategy.trace.callback;

public interface TraceCallback<T> {
    T call();
}

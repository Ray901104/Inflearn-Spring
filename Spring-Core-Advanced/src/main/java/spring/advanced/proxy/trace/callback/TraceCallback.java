package spring.advanced.proxy.trace.callback;

public interface TraceCallback<T> {
    T call();
}

package spring.advanced.templatecallbackstrategy.trace.logtrace;

import spring.advanced.templatecallbackstrategy.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}

package hello.advanced.app.trace.hellotrace;

import hello.advanced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV1Test {

    @Test
    public void begin() throws Exception {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus status = helloTraceV1.begin("hello");
        helloTraceV1.end(status);
    }

    @Test
    public void begin_exception() throws Exception {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus status = helloTraceV1.begin("hello");
        helloTraceV1.exception(status, new IllegalStateException());
    }


}
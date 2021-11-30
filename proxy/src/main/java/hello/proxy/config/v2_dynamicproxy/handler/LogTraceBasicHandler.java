package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.springframework.util.PatternMatchUtils;

public class LogTraceBasicHandler implements InvocationHandler {

  private final Object target;
  private final LogTrace logTrace;
  private final String[] patterns;

  public LogTraceBasicHandler(Object target, LogTrace logTrace, String[] patterns) {
    this.target = target;
    this.logTrace = logTrace;
    this.patterns = patterns;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    TraceStatus status = null;
    try {

      String methodName = method.getName();

      if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
        return method.invoke(target, args);
      }

      String message = method.getDeclaringClass().getSimpleName() + "." + methodName + "()";
      status = logTrace.begin(message);

      Object result = method.invoke(target, args);
      logTrace.end(status);

      return result;
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }
  }
}

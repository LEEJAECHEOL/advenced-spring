package com.advanced.springstudy.config.v2_dynamicproxy.handler;

import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class LogTraceBasicHandler implements InvocationHandler {
  private final Object target;
  private final LogTrace trace;

  public LogTraceBasicHandler(Object target, LogTrace trace) {
    this.target = target;
    this.trace = trace;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    TraceStatus status = null;

    try {
      String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
      status = trace.begin(message);

      Object result = method.invoke(target, args);

      trace.end(status);
      return result;
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}

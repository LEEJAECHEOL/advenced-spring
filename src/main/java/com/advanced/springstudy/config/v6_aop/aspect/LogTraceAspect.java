package com.advanced.springstudy.config.v6_aop.aspect;

import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class LogTraceAspect {
  private final LogTrace logTrace;

  public LogTraceAspect(LogTrace logTrace) {
    this.logTrace = logTrace;
  }

  @Around("execution(* com.advanced.springstudy.proxyapp..*(..))") // Pointcut
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // Advice
    TraceStatus status = null;

    try {
      String message = joinPoint.getSignature().toShortString();
      status = logTrace.begin(message);

      Object result = joinPoint.proceed();

      logTrace.end(status);
      return result;
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }
  }
}

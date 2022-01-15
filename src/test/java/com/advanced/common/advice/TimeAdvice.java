package com.advanced.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    log.info("TimeProxy Run");
    long startTime = System.currentTimeMillis();

    Object result = invocation.proceed(); // 타겟을 찾아서 타켓의 결과를 받아옴

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;

    log.info("TimeProxy exit resultTime = {}", resultTime);

    return result;
  }
}

package com.advanced.springstudy.config.v1_proxy.concrete_proxy;

import com.advanced.springstudy.proxyapp.v2.ProxyOrderRepositoryV2;
import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;

public class OrderRepositoryConcreteProxy extends ProxyOrderRepositoryV2 {
  private final ProxyOrderRepositoryV2 target;
  private final LogTrace trace;

  public OrderRepositoryConcreteProxy(ProxyOrderRepositoryV2 target, LogTrace trace) {
    this.target = target;
    this.trace = trace;
  }

  @Override
  public void save(String itemId) {
    TraceStatus status = null;

    try {
      status = trace.begin("OrderRepository.request()");
      target.save(itemId);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}

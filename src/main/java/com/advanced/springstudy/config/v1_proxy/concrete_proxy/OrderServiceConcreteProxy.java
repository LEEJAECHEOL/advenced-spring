package com.advanced.springstudy.config.v1_proxy.concrete_proxy;

import com.advanced.springstudy.proxyapp.v2.ProxyOrderRepositoryV2;
import com.advanced.springstudy.proxyapp.v2.ProxyOrderServiceV2;
import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends ProxyOrderServiceV2 {
  private final ProxyOrderServiceV2 target;
  private final LogTrace trace;

  public OrderServiceConcreteProxy(ProxyOrderServiceV2 target, LogTrace trace) {
    super(null);
    this.target = target;
    this.trace = trace;
  }

  @Override
  public void orderItem(String itemId) {
    TraceStatus status = null;

    try {
      status = trace.begin("OrderService.orderItem()");
      target.orderItem(itemId);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}

package com.advanced.springstudy.config.v1_proxy.concrete_proxy;

import com.advanced.springstudy.proxyapp.v2.ProxyOrderControllerV2;
import com.advanced.springstudy.proxyapp.v2.ProxyOrderServiceV2;
import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends ProxyOrderControllerV2 {
  private final ProxyOrderControllerV2 target;
  private final LogTrace trace;

  public OrderControllerConcreteProxy(ProxyOrderControllerV2 target, LogTrace trace) {
    super(null);
    this.target = target;
    this.trace = trace;
  }

  @Override
  public String request(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderController.request()");
      String result = target.request(itemId);
      trace.end(status);
      return result;
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }

  @Override
  public String noLog() {
    return target.noLog();
  }
}

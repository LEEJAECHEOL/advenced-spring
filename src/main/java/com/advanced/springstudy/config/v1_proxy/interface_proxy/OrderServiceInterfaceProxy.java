package com.advanced.springstudy.config.v1_proxy.interface_proxy;

import com.advanced.springstudy.proxyapp.v1.ProxyOrderServiceV1;
import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements ProxyOrderServiceV1 {
  private final ProxyOrderServiceV1 target;
  private final LogTrace trace;
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

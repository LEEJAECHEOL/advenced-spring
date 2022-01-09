package com.advanced.springstudy.config.v1_proxy.interface_proxy;

import com.advanced.springstudy.proxyapp.v1.ProxyOrderService;
import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements ProxyOrderService {
  private final ProxyOrderService target;
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

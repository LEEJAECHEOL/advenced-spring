package com.advanced.springstudy.config.v1_proxy.interface_proxy;

import com.advanced.springstudy.proxyapp.v1.ProxyOrderController;
import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements ProxyOrderController {
  private final ProxyOrderController target;
  private final LogTrace trace;
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

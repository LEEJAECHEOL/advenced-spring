package com.advanced.springstudy.config.v1_proxy.interface_proxy;

import com.advanced.springstudy.proxyapp.v1.ProxyOrderControllerV1;
import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxyV1 implements ProxyOrderControllerV1 {
  private final ProxyOrderControllerV1 target;
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

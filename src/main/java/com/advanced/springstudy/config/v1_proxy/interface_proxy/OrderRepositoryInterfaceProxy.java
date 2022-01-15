package com.advanced.springstudy.config.v1_proxy.interface_proxy;

import com.advanced.springstudy.proxyapp.v1.ProxyOrderRepositoryV1;
import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements ProxyOrderRepositoryV1 {
  private final ProxyOrderRepositoryV1 target;
  private final LogTrace trace;

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

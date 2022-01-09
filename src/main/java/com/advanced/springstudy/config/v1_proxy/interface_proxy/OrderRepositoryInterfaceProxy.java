package com.advanced.springstudy.config.v1_proxy.interface_proxy;

import com.advanced.springstudy.proxyapp.v1.ProxyOrderRepository;
import com.advanced.springstudy.trace.TraceStatus;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements ProxyOrderRepository {
  private final ProxyOrderRepository target;
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

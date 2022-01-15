package com.advanced.springstudy.config.v1_proxy;

import com.advanced.springstudy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxyV1;
import com.advanced.springstudy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import com.advanced.springstudy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import com.advanced.springstudy.proxyapp.v1.*;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
  @Bean
  public ProxyOrderControllerV1 orderControllerInterfaceProxy(LogTrace trace) {
    ProxyOrderControllerV1Impl controller = new ProxyOrderControllerV1Impl(orderServiceInterfaceProxy(trace));
    return new OrderControllerInterfaceProxyV1(controller, trace);
  }

  @Bean
  public ProxyOrderServiceV1 orderServiceInterfaceProxy(LogTrace trace) {
    ProxyOrderServiceV1Impl orderService = new ProxyOrderServiceV1Impl(proxyOrderRepository(trace));
    return new OrderServiceInterfaceProxy(orderService, trace);
  }

  @Bean
  public ProxyOrderRepositoryV1 proxyOrderRepository(LogTrace trace) {
    ProxyOrderRepositoryV1Impl orderRepository = new ProxyOrderRepositoryV1Impl();
    return new OrderRepositoryInterfaceProxy(orderRepository,trace);
  }

}

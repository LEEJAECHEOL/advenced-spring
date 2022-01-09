package com.advanced.springstudy.config.v1_proxy;

import com.advanced.springstudy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import com.advanced.springstudy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import com.advanced.springstudy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import com.advanced.springstudy.proxyapp.v1.*;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
  @Bean
  public ProxyOrderController orderControllerInterfaceProxy(LogTrace trace) {
    ProxyOrderControllerImpl controller = new ProxyOrderControllerImpl(orderServiceInterfaceProxy(trace));
    return new OrderControllerInterfaceProxy(controller, trace);
  }

  @Bean
  public ProxyOrderService orderServiceInterfaceProxy(LogTrace trace) {
    ProxyOrderServiceImpl orderService = new ProxyOrderServiceImpl(proxyOrderRepository(trace));
    return new OrderServiceInterfaceProxy(orderService, trace);
  }

  @Bean
  public ProxyOrderRepository proxyOrderRepository(LogTrace trace) {
    ProxyOrderRepositoryImpl orderRepository = new ProxyOrderRepositoryImpl();
    return new OrderRepositoryInterfaceProxy(orderRepository,trace);
  }

}

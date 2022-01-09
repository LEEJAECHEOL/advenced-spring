package com.advanced.springstudy.config.v2_dynamicproxy;

import com.advanced.springstudy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import com.advanced.springstudy.proxyapp.v1.*;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

  @Bean
  public ProxyOrderController proxyOrderController(LogTrace trace) {
    ProxyOrderController proxyOrderController = new ProxyOrderControllerImpl(proxyOrderService(trace));

    return (ProxyOrderController) Proxy.newProxyInstance(
      ProxyOrderController.class.getClassLoader(),
      new Class[]{ProxyOrderController.class},
      new LogTraceBasicHandler(proxyOrderController, trace)
    );
  }

  @Bean
  public ProxyOrderService proxyOrderService(LogTrace trace) {
    ProxyOrderService orderService = new ProxyOrderServiceImpl(proxyOrderRepository(trace));

    return (ProxyOrderService) Proxy.newProxyInstance(
      ProxyOrderService.class.getClassLoader(),
      new Class[]{ProxyOrderService.class},
      new LogTraceBasicHandler(orderService, trace));
  }

  @Bean
  public ProxyOrderRepository proxyOrderRepository(LogTrace trace) {
    ProxyOrderRepository orderRepository = new ProxyOrderRepositoryImpl();

    return (ProxyOrderRepository) Proxy.newProxyInstance(
      ProxyOrderRepository.class.getClassLoader(),
      new Class[]{ProxyOrderRepository.class},
      new LogTraceBasicHandler(orderRepository, trace));
  }
}

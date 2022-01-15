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
  public ProxyOrderControllerV1 proxyOrderController(LogTrace trace) {
    ProxyOrderControllerV1 proxyOrderControllerV1 = new ProxyOrderControllerV1Impl(proxyOrderService(trace));

    return (ProxyOrderControllerV1) Proxy.newProxyInstance(
      ProxyOrderControllerV1.class.getClassLoader(),
      new Class[]{ProxyOrderControllerV1.class},
      new LogTraceBasicHandler(proxyOrderControllerV1, trace)
    );
  }

  @Bean
  public ProxyOrderServiceV1 proxyOrderService(LogTrace trace) {
    ProxyOrderServiceV1 orderService = new ProxyOrderServiceV1Impl(proxyOrderRepository(trace));

    return (ProxyOrderServiceV1) Proxy.newProxyInstance(
      ProxyOrderServiceV1.class.getClassLoader(),
      new Class[]{ProxyOrderServiceV1.class},
      new LogTraceBasicHandler(orderService, trace));
  }

  @Bean
  public ProxyOrderRepositoryV1 proxyOrderRepository(LogTrace trace) {
    ProxyOrderRepositoryV1 orderRepository = new ProxyOrderRepositoryV1Impl();

    return (ProxyOrderRepositoryV1) Proxy.newProxyInstance(
      ProxyOrderRepositoryV1.class.getClassLoader(),
      new Class[]{ProxyOrderRepositoryV1.class},
      new LogTraceBasicHandler(orderRepository, trace));
  }
}

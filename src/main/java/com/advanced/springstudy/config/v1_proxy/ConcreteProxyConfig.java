package com.advanced.springstudy.config.v1_proxy;

import com.advanced.springstudy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.advanced.springstudy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.advanced.springstudy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import com.advanced.springstudy.proxyapp.v2.ProxyOrderControllerV2;
import com.advanced.springstudy.proxyapp.v2.ProxyOrderRepositoryV2;
import com.advanced.springstudy.proxyapp.v2.ProxyOrderServiceV2;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

  @Bean
  public ProxyOrderControllerV2 proxyOrderControllerV2(LogTrace trace) {
    ProxyOrderControllerV2 controllerV2 = new ProxyOrderControllerV2(proxyOrderServiceV2(trace));
    return new OrderControllerConcreteProxy(controllerV2, trace);
  }

  @Bean
  public ProxyOrderServiceV2 proxyOrderServiceV2(LogTrace trace) {
    ProxyOrderServiceV2 orderServiceV2 = new ProxyOrderServiceV2(proxyOrderRepositoryV2(trace));
    return new OrderServiceConcreteProxy(orderServiceV2, trace);
  }

  @Bean
  public ProxyOrderRepositoryV2 proxyOrderRepositoryV2(LogTrace trace) {
    ProxyOrderRepositoryV2 proxyOrderRepositoryV2 = new ProxyOrderRepositoryV2();
    return new OrderRepositoryConcreteProxy(proxyOrderRepositoryV2, trace);
  }
}

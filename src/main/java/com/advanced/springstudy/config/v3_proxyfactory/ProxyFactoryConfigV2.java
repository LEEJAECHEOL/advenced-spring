package com.advanced.springstudy.config.v3_proxyfactory;

import com.advanced.springstudy.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.advanced.springstudy.proxyapp.v1.*;
import com.advanced.springstudy.proxyapp.v2.ProxyOrderControllerV2;
import com.advanced.springstudy.proxyapp.v2.ProxyOrderRepositoryV2;
import com.advanced.springstudy.proxyapp.v2.ProxyOrderServiceV2;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {
  @Bean
  public ProxyOrderControllerV2 orderController(LogTrace trace){
    ProxyOrderControllerV2 orderController = new ProxyOrderControllerV2(orderService(trace));

    ProxyFactory factory = new ProxyFactory(orderController);
    factory.addAdvisor(getAdvisor(trace));
    ProxyOrderControllerV2 proxy = (ProxyOrderControllerV2) factory.getProxy();
    log.info("ProxyFactory proxy = {}, target = {}", proxy.getClass(), orderController.getClass());
    return proxy;

  }

  @Bean
  public ProxyOrderServiceV2 orderService(LogTrace trace){
    ProxyOrderServiceV2 orderService = new ProxyOrderServiceV2(orderRepository(trace));

    ProxyFactory factory = new ProxyFactory(orderService);
    factory.addAdvisor(getAdvisor(trace));
    ProxyOrderServiceV2 proxy = (ProxyOrderServiceV2) factory.getProxy();
    log.info("ProxyFactory proxy = {}, target = {}", proxy.getClass(), orderService.getClass());
    return proxy;

  }

  @Bean
  public ProxyOrderRepositoryV2 orderRepository(LogTrace trace){
    ProxyOrderRepositoryV2 orderRepository = new ProxyOrderRepositoryV2();

    ProxyFactory factory = new ProxyFactory(orderRepository);
    factory.addAdvisor(getAdvisor(trace));

    ProxyOrderRepositoryV2 proxy = (ProxyOrderRepositoryV2) factory.getProxy();
    log.info("ProxyFactory proxy = {}, target = {}", proxy.getClass(), orderRepository.getClass());

    return proxy;
  }

  private Advisor getAdvisor(LogTrace trace) {
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("request*", "order*", "save*");
    LogTraceAdvice advice = new LogTraceAdvice(trace);
    return new DefaultPointcutAdvisor(pointcut, advice);
  }
}

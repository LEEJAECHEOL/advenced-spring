package com.advanced.springstudy.config.v3_proxyfactory;

import com.advanced.springstudy.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.advanced.springstudy.proxyapp.v1.*;
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
public class ProxyFactoryConfigV1 {
  @Bean
  public ProxyOrderControllerV1 orderController(LogTrace trace){
    ProxyOrderControllerV1 orderController = new ProxyOrderControllerV1Impl(orderService(trace));

    ProxyFactory factory = new ProxyFactory(orderController);
    factory.addAdvisor(getAdvisor(trace));
    ProxyOrderControllerV1 proxy = (ProxyOrderControllerV1) factory.getProxy();
    log.info("ProxyFactory proxy = {}, target = {}", proxy.getClass(), orderController.getClass());
    return proxy;

  }

  @Bean
  public ProxyOrderServiceV1 orderService(LogTrace trace){
    ProxyOrderServiceV1 orderService = new ProxyOrderServiceV1Impl(orderRepository(trace));

    ProxyFactory factory = new ProxyFactory(orderService);
    factory.addAdvisor(getAdvisor(trace));
    ProxyOrderServiceV1 proxy = (ProxyOrderServiceV1) factory.getProxy();
    log.info("ProxyFactory proxy = {}, target = {}", proxy.getClass(), orderService.getClass());
    return proxy;

  }

  @Bean
  public ProxyOrderRepositoryV1 orderRepository(LogTrace trace){
    ProxyOrderRepositoryV1Impl orderRepository = new ProxyOrderRepositoryV1Impl();

    ProxyFactory factory = new ProxyFactory(orderRepository);
    factory.addAdvisor(getAdvisor(trace));

    ProxyOrderRepositoryV1 proxy = (ProxyOrderRepositoryV1) factory.getProxy();
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

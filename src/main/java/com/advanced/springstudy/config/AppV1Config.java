package com.advanced.springstudy.config;

import com.advanced.springstudy.proxyapp.v1.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV1Config {
  @Bean
  public ProxyOrderController proxyOrderController() {
    return new ProxyOrderControllerImpl(proxyOrderService());
  }

  @Bean
  public ProxyOrderService proxyOrderService() {
    return new ProxyOrderServiceImpl(proxyOrderRepository());
  }

  @Bean
  public ProxyOrderRepository proxyOrderRepository() {
    return new ProxyOrderRepositoryImpl();
  }

}

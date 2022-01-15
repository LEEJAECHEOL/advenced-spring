package com.advanced.springstudy.config.v6_aop;

import com.advanced.springstudy.config.v6_aop.aspect.LogTraceAspect;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {
  @Bean
  public LogTraceAspect logTraceAspect(LogTrace trace) {
    return new LogTraceAspect(trace);
  }
}

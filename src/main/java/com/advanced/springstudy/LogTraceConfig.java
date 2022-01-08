package com.advanced.springstudy;

import com.advanced.springstudy.trace.logtrace.LogTrace;
import com.advanced.springstudy.trace.logtrace.ThreadLocalFieldLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
  @Bean
  public LogTrace logTrace() {
    return new ThreadLocalFieldLogTrace();
  }
}

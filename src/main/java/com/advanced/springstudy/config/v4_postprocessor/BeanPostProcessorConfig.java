package com.advanced.springstudy.config.v4_postprocessor;

import com.advanced.springstudy.config.AppV1Config;
import com.advanced.springstudy.config.AppV2Config;
import com.advanced.springstudy.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.advanced.springstudy.config.v4_postprocessor.postporcessor.PackageLogTracePostProcessor;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
//@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

  @Bean
  public PackageLogTracePostProcessor logTracePostProcessor(LogTrace trace) {
    return new PackageLogTracePostProcessor("com.advanced", getAdvisor(trace));
  }

  private Advisor getAdvisor(LogTrace trace) {
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("request*", "order*", "save*");
    LogTraceAdvice advice = new LogTraceAdvice(trace);
    return new DefaultPointcutAdvisor(pointcut, advice);
  }
}

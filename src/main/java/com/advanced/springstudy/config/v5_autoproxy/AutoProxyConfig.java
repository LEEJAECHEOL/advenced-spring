package com.advanced.springstudy.config.v5_autoproxy;

import com.advanced.springstudy.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoProxyConfig {

  // 모든 Advisor를 조회해서 자동으로 등록됨.
  // 자동 프록시 생성기는 모든 스프링 빈에 프록시를 적용하는 것이 아니라 포인트컷으로 한번 필터링해서 어드바이스가 사용될 가능성이 있는 곳에만 프록시를 생성
//  @Bean
//  public Advisor advisor1(LogTrace trace) {
//    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
//    pointcut.setMappedNames("request*", "order*", "save*");
//    LogTraceAdvice advice = new LogTraceAdvice(trace);
//    return new DefaultPointcutAdvisor(pointcut, advice);
//  }
//
  @Bean
  public Advisor advisor2(LogTrace trace) {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression("execution(* com.advanced.springstudy.proxyapp..*(..)) && !execution(* com.advanced.springstudy.proxyapp..noLog(..))");

    LogTraceAdvice advice = new LogTraceAdvice(trace);
    return new DefaultPointcutAdvisor(pointcut, advice);
  }
}

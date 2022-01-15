package com.advanced.springstudy;

import com.advanced.springstudy.config.AppV1Config;
import com.advanced.springstudy.config.AppV2Config;
import com.advanced.springstudy.config.v1_proxy.ConcreteProxyConfig;
import com.advanced.springstudy.config.v1_proxy.InterfaceProxyConfig;
import com.advanced.springstudy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import com.advanced.springstudy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import com.advanced.springstudy.config.v3_proxyfactory.ProxyFactoryConfigV1;
import com.advanced.springstudy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import com.advanced.springstudy.config.v4_postprocessor.BeanPostProcessorConfig;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import com.advanced.springstudy.trace.logtrace.ThreadLocalFieldLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreteProxyConfig.class)
//@Import(DynamicProxyBasicConfig.class)
//@Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import(ProxyFactoryConfigV2.class)
@Import(BeanPostProcessorConfig.class)
@SpringBootApplication(scanBasePackages = "com.advanced.springstudy.proxyapp")
public class SpringStudyApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringStudyApplication.class, args);
  }

  @Bean
  public LogTrace logTrace(){
    return new ThreadLocalFieldLogTrace();
  }

}

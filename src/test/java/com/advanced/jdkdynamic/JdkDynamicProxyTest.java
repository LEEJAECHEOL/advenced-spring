package com.advanced.jdkdynamic;

import com.advanced.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

  @Test
  void dynamicA() {
    AInterface target = new AInterfaceImpl();

    TimeInvocationHandler handler = new TimeInvocationHandler(target);

    AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

    proxy.call();

    log.info("targetClass : {}", target.getClass());
    log.info("proxtClass : {}", proxy.getClass());
  }
  @Test
  void dynamicB() {
    BInterface target = new BInterfaceImpl();

    TimeInvocationHandler handler = new TimeInvocationHandler(target);

    BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

    proxy.call();

    log.info("targetClass : {}", target.getClass());
    log.info("proxtClass : {}", proxy.getClass());
  }
}

package com.advanced.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
  @Test
  void reflection0() {
    Hello target = new Hello();

    // 공통 로직1 시작
    log.info("start");
    String result = target.callA(); // 호출하는 메서드만 다름
    log.info("result : {}", result);
    log.info("end");
    // 공통 로직1 종료

    // 공통 로직2 시작
    log.info("start");
    String result2 = target.callB(); // 호출하는 메서드만 다름
    log.info("result : {}", result2);
    log.info("end");
    // 공통 로직2 종료
  }

  @Test
  void reflection1() throws Exception {
    Class classHello = Class.forName("com.advanced.jdkdynamic.ReflectionTest$Hello");

    Hello target = new Hello();
    // callA method info
    Method methodCallA = classHello.getMethod("callA");
    Object result1 = methodCallA.invoke(target);
    log.info("result1 = {}", result1);

    Method methodCallB = classHello.getMethod("callB");
    Object result2 = methodCallB.invoke(target);
    log.info("result2 = {}", result2);
  }

  @Test
  void reflection2() throws Exception {
    Class classHello = Class.forName("com.advanced.jdkdynamic.ReflectionTest$Hello");

    Hello target = new Hello();
    // callA method info
    Method methodCallA = classHello.getMethod("callA");
    dynamicCall(methodCallA, target);

    Method methodCallB = classHello.getMethod("callB");
    dynamicCall(methodCallB, target);
  }

  private void dynamicCall(Method method, Object target) throws Exception {
    log.info("start");
    Object result = method.invoke(target);
    log.info("result = {}", result);
  }

  @Slf4j
  static class Hello {
    public String callA() {
      log.info("callA");
      return "A";
    }
    public String callB() {
      log.info("callB");
      return "B";
    }

  }
}

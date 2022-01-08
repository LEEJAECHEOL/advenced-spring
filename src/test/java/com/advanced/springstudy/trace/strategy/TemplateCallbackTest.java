package com.advanced.springstudy.trace.strategy;

import com.advanced.springstudy.trace.strategy.code.strategy.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

  @Test
  void callbackV1() {
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(() -> log.info("비즈니스 1 실행"));
    template.execute(() -> log.info("비즈니스 2 실행"));
  }
}

package com.advanced.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AInterfaceImpl implements AInterface {
  @Override
  public String call() {
    log.info("A call");
    return "A";
  }
}

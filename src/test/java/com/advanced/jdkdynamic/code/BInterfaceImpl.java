package com.advanced.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BInterfaceImpl implements BInterface {

  @Override
  public String call() {
    log.info("B call");
    return "B";
  }
}

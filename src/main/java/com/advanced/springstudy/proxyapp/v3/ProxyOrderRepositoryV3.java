package com.advanced.springstudy.proxyapp.v3;

import org.springframework.stereotype.Repository;

@Repository
public class ProxyOrderRepositoryV3 {
  public void save(String itemId) {
    if (itemId.equals("ex")) {
      throw new IllegalStateException("예외");
    }
    sleep(1000);
  }

  private void sleep(int i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

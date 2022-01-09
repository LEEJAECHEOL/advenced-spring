package com.advanced.springstudy.proxyapp.v1;

public class ProxyOrderRepositoryImpl implements ProxyOrderRepository {
  @Override
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

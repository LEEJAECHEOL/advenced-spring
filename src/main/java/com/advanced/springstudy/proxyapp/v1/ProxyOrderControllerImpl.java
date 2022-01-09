package com.advanced.springstudy.proxyapp.v1;

public class ProxyOrderControllerImpl implements ProxyOrderController {
  private final ProxyOrderService orderService;

  public ProxyOrderControllerImpl(ProxyOrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public String request(String itemId) {
    orderService.orderItem(itemId);
    return "ok";
  }

  @Override
  public String noLog() {
    return "ok";
  }
}

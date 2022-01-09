package com.advanced.springstudy.proxyapp.v1;

public class ProxyOrderServiceImpl implements ProxyOrderService {
  private final ProxyOrderRepository orderRepository;

  public ProxyOrderServiceImpl(ProxyOrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void orderItem(String itemId) {
    orderRepository.save(itemId);
  }
}

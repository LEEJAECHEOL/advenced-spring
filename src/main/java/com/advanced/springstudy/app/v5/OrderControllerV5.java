package com.advanced.springstudy.app.v5;

import com.advanced.springstudy.trace.callback.TraceTemplate;
import com.advanced.springstudy.trace.logtrace.LogTrace;
import com.advanced.springstudy.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {
  private final OrderServiceV5 orderService;
  private final TraceTemplate template;

  // 생성자 말고 spring bean을 주입하는 방법도 있음
  public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
    this.orderService = orderService;
    this.template = new TraceTemplate(trace);
  }

  @GetMapping("/v5/request")
  public String request(String itemId) {
    return template.execute("OrderController.request()", () -> {
      orderService.orderItem(itemId);
      return "ok";
    });
  }
}

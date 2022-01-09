package com.advanced.springstudy.proxyapp.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링컨트롤러로 인식할 수 있음.
@RequestMapping
@ResponseBody
public interface ProxyOrderController {
  @GetMapping("/v1/proxy/request")
  String request(@RequestParam("itemId") String itemId);

  @GetMapping("/v1/no-log")
  String noLog();
}

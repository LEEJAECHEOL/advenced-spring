package com.advanced.springstudy.pureproxy.concreteproxy;

import com.advanced.springstudy.pureproxy.concreteproxy.code.ConcreteClient;
import com.advanced.springstudy.pureproxy.concreteproxy.code.ConcreteLogic;
import com.advanced.springstudy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcretProxyTest {

  @Test
  void noProxy() {
    ConcreteLogic concreteLogic = new ConcreteLogic();
    ConcreteClient client = new ConcreteClient(concreteLogic);
    client.execute();
  }

  @Test
  void addProxy() {
    ConcreteLogic concreteLogic = new ConcreteLogic();
    TimeProxy timeProxy = new TimeProxy(concreteLogic);
    ConcreteClient client = new ConcreteClient(timeProxy);
    client.execute();
  }
}

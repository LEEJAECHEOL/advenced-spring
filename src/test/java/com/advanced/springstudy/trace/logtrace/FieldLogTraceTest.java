package com.advanced.springstudy.trace.logtrace;

import com.advanced.springstudy.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {
  FieldLogTrace trace = new FieldLogTrace();

  @Test
  void begion_end_level2() {
    TraceStatus status1 = trace.begin("hello1");
    TraceStatus status2 = trace.begin("hello2");
    trace.end(status2);
    trace.end(status1);
  }

  @Test
  void begin_exception_level2(){
    TraceStatus status1 = trace.begin("hello1");
    TraceStatus status2 = trace.begin("hello2");
    trace.exception(status2, new Exception());
    trace.exception(status1, new Exception());
  }
}
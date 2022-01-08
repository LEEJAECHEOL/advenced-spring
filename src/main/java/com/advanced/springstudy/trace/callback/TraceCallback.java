package com.advanced.springstudy.trace.callback;

public interface TraceCallback<T> {
  T call();
}

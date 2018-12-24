package org.sselab.iot.platform.util;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionalFunction<T, R> {

  R apply(T x) throws Exception;

  static <T, R> Function<T, R> unchecked(ExceptionalFunction<T, R> f) {
    return x -> {
      try {
        return f.apply(x);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

}

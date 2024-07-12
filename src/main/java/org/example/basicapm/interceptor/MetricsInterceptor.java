package org.example.basicapm.interceptor;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MetricsInterceptor implements HandlerInterceptor {

  private static final String START_TIME = "startTime";

  private final MeterRegistry meterRegistry;

  public MetricsInterceptor(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    long startTime = System.currentTimeMillis();
    request.setAttribute(START_TIME, startTime);
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    long startTime = (Long) request.getAttribute(START_TIME);
    long endTime = System.currentTimeMillis();
    long responseTime = endTime - startTime;

    Timer.builder("http.requests")
        .tag("uri", request.getRequestURI())
        .tag("method", request.getMethod())
        .register(meterRegistry)
        .record(responseTime, TimeUnit.MILLISECONDS);

    System.out.println(
        "Request URL: "
            + request.getRequestURL().toString()
            + " | Response Time: "
            + responseTime
            + "ms");
  }
}

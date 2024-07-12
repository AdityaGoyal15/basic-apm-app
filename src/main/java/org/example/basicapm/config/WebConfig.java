package org.example.basicapm.config;

import org.example.basicapm.interceptor.MetricsInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

  private final MetricsInterceptor metricsInterceptor;

  public WebConfig(MetricsInterceptor metricsInterceptor) {
    this.metricsInterceptor = metricsInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(metricsInterceptor);
  }
}

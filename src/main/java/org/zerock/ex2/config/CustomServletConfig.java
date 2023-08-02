package org.zerock.ex2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.ex2.controller.formatter.LocalDateFormatter;

@Configuration
public class CustomServletConfig implements WebMvcConfigurer{

  @Override
  public void addFormatters(FormatterRegistry registry) {
    
    registry.addFormatter(new LocalDateFormatter());
  }

}
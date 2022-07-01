package com.feib.integration.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
  
  private static final String API_PKG = "com.feib.integration.api.controller";
  private static final String API_TITLE = "FEIB CreditCard Integration API.";
  private static final String API_DESC = "Integration API.";
  private static final String API_VERSION = "API 1.0.0";
  private static final String API_TERM_URL = "Terms of service";
  private static final String API_LIC = "License of API";
  private static final String API_LIC_URL = "API license URL";
  private static final String API_CONTACT_NAME = "Eric Wei";
  private static final String API_CONTACT_URL = "https://www.feib.com.tw/";
  private static final String API_CONTACT_EMAIL = "ericwei@feib.com.tw";
  
  @Bean
  public Docket api() {
    return new Docket(
        DocumentationType.OAS_30)
        .select()
        .apis(RequestHandlerSelectors.basePackage(API_PKG))
        //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }
  
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(API_TITLE)
        .description(API_DESC)
        .version(API_VERSION)
        .termsOfServiceUrl(API_TERM_URL)
        .contact(new Contact(API_CONTACT_NAME, API_CONTACT_URL, API_CONTACT_EMAIL))
        .license(API_LIC)
        .licenseUrl(API_LIC_URL)
        .build();
  }
  
}

package com.feib.business.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

/**
 * For deploy to application server and running in stand alone mode, first the application entry
 * class should extend SpringBootServletInitializer, second the application entry class should
 * override "configure" method of SpringBootServletInitializer, third has the public static "main"
 * method. 
 * swagger doc: https://host/path/swagger/index.html 
 * API: https://host/path/xxx/v1/yyy
 */
@SpringBootApplication
public class FeibCreditCardBusinessServiceApplication extends SpringBootServletInitializer {
  private static final Logger log = LoggerFactory.getLogger(FeibCreditCardBusinessServiceApplication.class);

  public static void main(String[] args) {
    //-Dsun.net.client.defaultConnectTimeout=<TimeoutInMiliSec>
    //-Dsun.net.client.defaultReadTimeout=<TimeoutInMiliSec>
    //SpringApplication.run(FeibCreditCardBusinessServiceApplication.class, args);
    SpringApplication app = new SpringApplication(FeibCreditCardBusinessServiceApplication.class);
    app.addListeners(new ApplicationPidFileWriter());
    Environment env = app.run(args).getEnvironment();
    log.info("URL: \n\thttp://localhost:{}{}\n\t", 
            env.getProperty("server.port"),
            env.getProperty("server.servlet.context-path"));
  }
  
  //@Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(FeibCreditCardBusinessServiceApplication.class);
  } 

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

}

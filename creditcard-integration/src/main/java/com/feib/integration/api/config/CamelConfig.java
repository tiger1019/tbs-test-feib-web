package com.feib.integration.api.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class CamelConfig {
  
  @Resource 
  private Environment env;
  
  @Bean
  public ConnectionFactory rabbitConnectionFactory() {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost(env.getProperty("spring.rabbitmq.host"));
      factory.setPort(Integer.valueOf(env.getProperty("spring.rabbitmq.port")));
      factory.setAutomaticRecoveryEnabled(true);
      factory.setUsername(env.getProperty("spring.rabbitmq.username"));
      factory.setPassword(env.getProperty("spring.rabbitmq.password"));
      return factory;
  }
}

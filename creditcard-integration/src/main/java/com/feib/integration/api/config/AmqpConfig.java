package com.feib.integration.api.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.feib.integration.channel.ChannelConstants;
import com.feib.integration.channel.LogMessageExceptionHandler;
import com.feib.integration.channel.MessageExceptionHandler;


@Configuration
public class AmqpConfig {
    
  @Bean
  public Queue int9013ReqQueue() {
    return new Queue(ChannelConstants.INT_REQ_9013, false);
  }
  
  @Bean
  public Queue int9014ReqQueue() {
    return new Queue(ChannelConstants.INT_REQ_9014, false);
  }
  
  @Bean
  @Order(1)
  public MessageExceptionHandler logMessageExceptionHandler() {
      return new LogMessageExceptionHandler();
  }
  

}

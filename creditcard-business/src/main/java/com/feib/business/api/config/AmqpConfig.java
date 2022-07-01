package com.feib.business.api.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.feib.integration.channel.LogMessageExceptionHandler;
import com.feib.integration.channel.MessageExceptionHandler;

//@Configuration
public class AmqpConfig {

  private static final String REP_QUEUE_NAME = "BusinessRepQueue";
  
  @Autowired
  private RabbitTemplate rabbitTemplate;
  
  @Autowired
  private ConnectionFactory connectionFactory;
    
  @Bean
  public Queue BusinessRepQueue() {
    return new Queue(REP_QUEUE_NAME, false);
  }

  @Bean
  @Order(1)
  public MessageExceptionHandler logMessageExceptionHandler() {
      return new LogMessageExceptionHandler();
  }
  
  @Bean
  public AsyncRabbitTemplate template() {
      SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
      container.setQueueNames(REP_QUEUE_NAME);
      return new AsyncRabbitTemplate(rabbitTemplate, container);
  }
  
}

package com.feib.business.api.adapter.impl;

//import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feib.business.api.adapter.BaseIntegrationAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseIntegration<I, O> implements BaseIntegrationAdapter<I, O>, ListenableFutureCallback<O> {

  @Autowired
  protected RabbitTemplate rabbitTemplate;
  
  //@Autowired
  //protected AsyncRabbitTemplate asyncRabbitTemplate;
    
  @Autowired
  protected RestTemplate restTemplate;
  
  private final ObjectMapper objectMapper = new ObjectMapper();
  
  protected Class<O> returnClass;
  
  protected String apiUrl;
  
  protected String channelName;
  
  public BaseIntegration() {}
  
  public BaseIntegration(String channelName, String apiUrl) {
    this.channelName = channelName;
    this.apiUrl = apiUrl;
  }
  
  public String getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  @Override
  public abstract void onSuccess(O result);

  @Override
  public abstract void onFailure(Throwable ex);

  @SuppressWarnings("unchecked")
  @Override
  public O ByEvent(I vo) {
    O ret = null;
    Object obj = null;
    log.debug(">>>ByEvent {} {}", channelName, vo.toString());
    //呼叫 
    try {
      obj = rabbitTemplate.convertSendAndReceive(channelName, vo);       
    } catch(Exception e) {
      log.error(e.getMessage(), e);
    }
    
    log.debug("<<<");
    if (obj == null) {
      log.debug("rabbitTemplate get null object");
      return ret;        
    }
    ret = (O) obj;  
    log.debug("ret {}", ret.toString());
    return ret;
  }

  @Override
  public void ByAsyncEvent(I vo) {
    //AsyncRabbitTemplate.RabbitConverterFuture<O> future =
    //    asyncRabbitTemplate.convertSendAndReceive(channelName, vo);
    //future.addCallback(this);
  }

  @Override
  public O ByApi(I vo) {
    log.debug(">>>ByAPI {}", vo.toString());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    String req = null;
    try {
      
      req = objectMapper.writeValueAsString(vo);
    } catch (JsonProcessingException e) {
      log.error("Convert json error ", e);
      throw new IllegalStateException("Convert Fail"); 
    }
    log.debug("Req = {}", req);
    HttpEntity<String> request = new HttpEntity<String>(req, headers);
    
    O rep = restTemplate.postForObject(apiUrl, request, returnClass);
    log.debug("Rep = {}", rep);
    
    return rep;
  }
  

}

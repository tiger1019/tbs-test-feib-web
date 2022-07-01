package com.feib.business.api.adapter.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.feib.business.api.adapter.Integration9013;
import com.feib.integration.api.entity.IVR9013RequestVO;
import com.feib.integration.api.entity.IVR9013ResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Integration9013Impl extends BaseIntegration<IVR9013RequestVO, IVR9013ResponseVO> implements Integration9013  {

  @Value("${integration.int9013.url}")
  private String apiUrl9013;
  
  @Value("${integration.int9013.channel}")
  private String channelName9013;
  
  public Integration9013Impl() {
    super();
    this.returnClass = IVR9013ResponseVO.class;    
  }
  
  public Integration9013Impl(String channelName, String apiUrl) {
    super(channelName, apiUrl);
    this.returnClass = IVR9013ResponseVO.class;
  }
  
  @Override
  public void onSuccess(IVR9013ResponseVO result) {
    log.debug("Get result {}", result.toString());
  }

  @Override
  public void onFailure(Throwable ex) {
    log.error(ex.getMessage());
  }
  
  @PostConstruct
  private void postConstruct() {
    this.apiUrl = this.apiUrl9013;
    this.channelName = this.channelName9013;
  }
  
}

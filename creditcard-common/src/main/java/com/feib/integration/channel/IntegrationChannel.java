package com.feib.integration.channel;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.feib.integration.api.entity.IVR9013RequestVO;
import com.feib.integration.api.entity.IVR9013ResponseVO;
import com.feib.integration.api.entity.IVR9014RequestVO;
import com.feib.integration.api.entity.IVR9014ResponseVO;

public interface IntegrationChannel {
  @RabbitListener(queues = ChannelConstants.INT_REQ_9013)  
  public IVR9013ResponseVO processIntReq9013(IVR9013RequestVO input);
  
  @RabbitListener(queues = ChannelConstants.INT_REQ_9014)  
  public IVR9014ResponseVO processIntReq9014(IVR9014RequestVO input);
}

package com.feib.business.api.entity;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TransactionDetailsRequestVO", description = "交易明細回覆檔")
public class TransactionDetailsResponseVO implements Serializable, Cloneable {
  
  private static final long serialVersionUID = -845623738296663501L;
  
  @ApiModelProperty("消費明細清單")
  private List<TransactionDetailsPurchaseVO> purchase;

}

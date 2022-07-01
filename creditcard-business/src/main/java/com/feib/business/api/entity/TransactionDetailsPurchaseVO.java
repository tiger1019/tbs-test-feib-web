package com.feib.business.api.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "TransactionDetailsPurchaseVO", description = "交易明細")
public class TransactionDetailsPurchaseVO implements Serializable, Cloneable {

  private static final long serialVersionUID = -6878458520194203913L;

  @ApiModelProperty("卡號")
  private String cardNo;

  @ApiModelProperty("卡別")
  private String cardType;

  @ApiModelProperty("消費日期")
  private String purchaseDate;

  @ApiModelProperty("出帳日期")
  private String postingDate;

  @ApiModelProperty("授權碼")
  private String authCode;

  @ApiModelProperty("描述")
  private String description;

  @ApiModelProperty("消費金額")
  private double localAmount;

}

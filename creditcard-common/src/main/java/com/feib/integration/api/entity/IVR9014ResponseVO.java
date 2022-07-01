package com.feib.integration.api.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class IVR9014ResponseVO implements Serializable {
	
  private static final long serialVersionUID = -2559799562776801695L;

  private String txnID = "IVR9014"; // 交易代號

	private String unitCD;

	private String sendDate;

	private String sendTime;

	private String reqUserID;

	private String processStatus;

	private String uniqueNo;

	private String messageType;

	private String messageRecNo;

	private String reserve;

	private String transactionCode;

	private String destinationAmt;

	private String sourceAmt;

	private String authorization;

	private String processDate;

	private String realCardNo;

	private String billType;

	private String acquireDate;

	private String enterDate;

	private String fileNo;

	private String sourceCurrency;

	private String setllementAmt;

	private String stmtCycle;

	private String purchaseDate;

	private String closingDate;

	private String destinationAmtExp;

	private String merchantEngName;

	private String merchantNo;

	private String merchantCountry;

	private String mccrAmt;

	private String so;

	private String merchantChiName;

	private String si;

	private String merchantCity;

	private String merchantCategory;

	private String issueSurcharge;

	private String instAmtNobill;

	private String instYearRate;

	private String virtualCardNo;

	private String filler;

}

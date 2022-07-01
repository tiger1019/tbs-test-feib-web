package com.feib.integration.api.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class IVR9013ResponseDetailVO implements Serializable {

  private static final long serialVersionUID = -4350428220291123584L;
  private String seq; // 序號
	private String acquireDate; // 清算日 西元年月日
	private String purchaseDate; // 消費日 西元年月日
	private String destinatiionAmt; // 台幣金額 ---------9
	private String sourceAmt; // 外幣金額 ---------9.99
	private String cardType; // 卡別 例:VC0,MG1
	private String destinationCurrency; // 幣別
	private String merchantCountry; // 國家
	private String stmtCycle; // 關帳日期
	private String revolveIntRateYear; // 年利率 Z9.99
	private String merchantName; // 特店中文名稱 全型字
	private String enterDate; // 入帳日 西元年月日

}

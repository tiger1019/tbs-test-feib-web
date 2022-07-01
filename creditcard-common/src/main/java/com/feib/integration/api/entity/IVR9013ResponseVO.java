package com.feib.integration.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class IVR9013ResponseVO implements Serializable {
	
  private static final long serialVersionUID = 8105369867891649757L;
  private String txID = "IVR9013"; // 交易代號 "IVR9013"
	private String unitCD; // 單位代碼/回覆碼 "0000" 表示成功 非"0000" 表示異常
	private String sendDate; // 西元年月日
	private String sendTime; // 時分秒微秒
	private String reqUserID; // 需求者代碼 空白(目前未使用)
	private String processStatus; // 空白(目前未使用)
	private String uniqueNO; // 與上行電文同序號
	private String messageType; // "o" 表示結束電文
	private String messageRecNo; // “0001” 目前未使用
	private String reserve; // "0"
	private String lastpayDD; // 繳款截止日
	private String totReceivable; // 本期應繳總額 ---------9
	private String minPayOrigina; // 本期最低應繳金額 ---------9
	private List<IVR9013ResponseDetailVO> detail = new ArrayList<IVR9013ResponseDetailVO>();
	private String flag; // 本期有無帳單資料 N=無ACCT
	private String filler; // 保留 SPACE

}

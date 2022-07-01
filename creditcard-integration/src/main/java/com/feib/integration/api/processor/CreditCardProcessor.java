package com.feib.integration.api.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feib.integration.api.entity.IVR9013RequestVO;
import com.feib.integration.api.entity.IVR9013ResponseDetailVO;
import com.feib.integration.api.entity.IVR9013ResponseVO;
import com.feib.integration.api.entity.IVR9014RequestVO;
import com.feib.integration.api.entity.IVR9014ResponseVO;
import com.feib.integration.channel.IntegrationChannel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CreditCardProcessor implements IntegrationChannel {

  private static Map<String, IVR9013ResponseVO> ivr9013ResponseMap;

  private static Map<String, IVR9014ResponseVO> ivr9014ResponseMap;


  static {
    ivr9013ResponseMap = new HashMap<String, IVR9013ResponseVO>();
    IVR9013ResponseVO ivr9013ResponseVO1 = new IVR9013ResponseVO();
    ivr9013ResponseVO1.setTxID("IVR9013");
    ivr9013ResponseVO1.setUnitCD("0000");
    ivr9013ResponseVO1.setSendDate("20220123");
    ivr9013ResponseVO1.setSendTime("14515200");
    ivr9013ResponseVO1.setReqUserID("A123XXX789");
    ivr9013ResponseVO1.setUniqueNO("2514515397");
    ivr9013ResponseVO1.setMessageType("o");
    ivr9013ResponseVO1.setMessageRecNo("0001");
    ivr9013ResponseVO1.setTotReceivable("59858");
    ivr9013ResponseVO1.setMinPayOrigina("5986");

    List<IVR9013ResponseDetailVO> ivr9013ResponseDetails1 =
        new ArrayList<IVR9013ResponseDetailVO>();
    IVR9013ResponseDetailVO IVR9013ResponseDetailVO1 = new IVR9013ResponseDetailVO();
    IVR9013ResponseDetailVO1.setSeq("0001");
    IVR9013ResponseDetailVO1.setDestinatiionAmt("500");
    IVR9013ResponseDetailVO1.setDestinationCurrency("901");
    IVR9013ResponseDetailVO1.setRevolveIntRateYear("10.84");
    IVR9013ResponseDetailVO1.setMerchantName("悠遊卡自動加值－統一超商");
    IVR9013ResponseDetailVO1.setAcquireDate("20220121");
    IVR9013ResponseDetailVO1.setPurchaseDate("20220123");
    ivr9013ResponseDetails1.add(IVR9013ResponseDetailVO1);

    IVR9013ResponseDetailVO IVR9013ResponseDetailVO2 = new IVR9013ResponseDetailVO();
    IVR9013ResponseDetailVO2.setSeq("0002");
    IVR9013ResponseDetailVO2.setDestinatiionAmt("3889");
    IVR9013ResponseDetailVO2.setDestinationCurrency("901");
    IVR9013ResponseDetailVO2.setRevolveIntRateYear("10.84");
    IVR9013ResponseDetailVO2.setMerchantName("電商平台消費");
    IVR9013ResponseDetailVO2.setAcquireDate("20220101");
    IVR9013ResponseDetailVO2.setPurchaseDate("20220103");
    ivr9013ResponseDetails1.add(IVR9013ResponseDetailVO2);

    IVR9013ResponseDetailVO IVR9013ResponseDetailVO3 = new IVR9013ResponseDetailVO();
    IVR9013ResponseDetailVO3.setSeq("0003");
    IVR9013ResponseDetailVO3.setDestinatiionAmt("2200");
    IVR9013ResponseDetailVO3.setDestinationCurrency("901");
    IVR9013ResponseDetailVO3.setRevolveIntRateYear("10.84");
    IVR9013ResponseDetailVO3.setMerchantName("賣場消費");
    IVR9013ResponseDetailVO3.setAcquireDate("20220111");
    IVR9013ResponseDetailVO3.setPurchaseDate("20220113");
    ivr9013ResponseDetails1.add(IVR9013ResponseDetailVO3);

    ivr9013ResponseVO1.setDetail(ivr9013ResponseDetails1);
    ivr9013ResponseMap.put(ivr9013ResponseVO1.getReqUserID(), ivr9013ResponseVO1);

    IVR9013ResponseVO ivr9013ResponseVO2 = new IVR9013ResponseVO();
    ivr9013ResponseVO2.setTxID("IVR9013");
    ivr9013ResponseVO2.setUnitCD("0000");
    ivr9013ResponseVO2.setSendDate("20220123");
    ivr9013ResponseVO2.setSendTime("18225200");
    ivr9013ResponseVO2.setReqUserID("B987XXX321");
    ivr9013ResponseVO2.setUniqueNO("5153251497");
    ivr9013ResponseVO2.setMessageType("o");
    ivr9013ResponseVO2.setMessageRecNo("0001");
    ivr9013ResponseVO2.setTotReceivable("15689");
    ivr9013ResponseVO2.setMinPayOrigina("1569");

    List<IVR9013ResponseDetailVO> ivr9013ResponseDetails2 =
        new ArrayList<IVR9013ResponseDetailVO>();
    IVR9013ResponseDetailVO IVR9013ResponseDetailVO4 = new IVR9013ResponseDetailVO();
    IVR9013ResponseDetailVO4.setSeq("0001");
    IVR9013ResponseDetailVO4.setDestinatiionAmt("200");
    IVR9013ResponseDetailVO4.setDestinationCurrency("901");
    IVR9013ResponseDetailVO4.setRevolveIntRateYear("10.84");
    IVR9013ResponseDetailVO4.setMerchantName("停車費用");
    IVR9013ResponseDetailVO4.setAcquireDate("20220101");
    IVR9013ResponseDetailVO4.setPurchaseDate("20220103");
    ivr9013ResponseDetails2.add(IVR9013ResponseDetailVO4);

    IVR9013ResponseDetailVO IVR9013ResponseDetailVO5 = new IVR9013ResponseDetailVO();
    IVR9013ResponseDetailVO5.setSeq("0002");
    IVR9013ResponseDetailVO5.setDestinatiionAmt("1234");
    IVR9013ResponseDetailVO5.setDestinationCurrency("901");
    IVR9013ResponseDetailVO5.setRevolveIntRateYear("10.84");
    IVR9013ResponseDetailVO5.setMerchantName("電信費用");
    IVR9013ResponseDetailVO5.setAcquireDate("20220102");
    IVR9013ResponseDetailVO5.setPurchaseDate("20220104");
    ivr9013ResponseDetails2.add(IVR9013ResponseDetailVO5);

    IVR9013ResponseDetailVO IVR9013ResponseDetailVO6 = new IVR9013ResponseDetailVO();
    IVR9013ResponseDetailVO6.setSeq("0003");
    IVR9013ResponseDetailVO6.setDestinatiionAmt("987");
    IVR9013ResponseDetailVO6.setDestinationCurrency("901");
    IVR9013ResponseDetailVO6.setRevolveIntRateYear("10.84");
    IVR9013ResponseDetailVO6.setMerchantName("水費");
    IVR9013ResponseDetailVO6.setAcquireDate("20220104");
    IVR9013ResponseDetailVO6.setPurchaseDate("20220105");
    ivr9013ResponseDetails2.add(IVR9013ResponseDetailVO6);

    IVR9013ResponseDetailVO IVR9013ResponseDetailVO7 = new IVR9013ResponseDetailVO();
    IVR9013ResponseDetailVO7.setSeq("0004");
    IVR9013ResponseDetailVO7.setDestinatiionAmt("8999");
    IVR9013ResponseDetailVO7.setDestinationCurrency("901");
    IVR9013ResponseDetailVO7.setRevolveIntRateYear("10.84");
    IVR9013ResponseDetailVO7.setMerchantName("KTV娛樂費");
    IVR9013ResponseDetailVO7.setAcquireDate("20220108");
    IVR9013ResponseDetailVO7.setPurchaseDate("20220110");
    ivr9013ResponseDetails2.add(IVR9013ResponseDetailVO7);

    ivr9013ResponseVO2.setDetail(ivr9013ResponseDetails2);
    ivr9013ResponseMap.put(ivr9013ResponseVO2.getReqUserID(), ivr9013ResponseVO2);

    ivr9014ResponseMap = new HashMap<String, IVR9014ResponseVO>();
    IVR9014ResponseVO ivr9014ResponseVO1 = new IVR9014ResponseVO();
    ivr9014ResponseVO1.setTxnID("IVR9014");
    ivr9014ResponseVO1.setUnitCD("0000");
    ivr9014ResponseVO1.setMessageRecNo("0001");
    ivr9014ResponseVO1.setReqUserID("A123XXX789");
    ivr9014ResponseVO1.setRealCardNo("1234XXXXXXXX3456");
    ivr9014ResponseMap.put(ivr9014ResponseVO1.getReqUserID() + ivr9014ResponseVO1.getMessageRecNo(),
        ivr9014ResponseVO1);

    IVR9014ResponseVO ivr9014ResponseVO2 = new IVR9014ResponseVO();
    ivr9014ResponseVO2.setTxnID("IVR9014");
    ivr9014ResponseVO2.setUnitCD("0000");
    ivr9014ResponseVO2.setMessageRecNo("0002");
    ivr9014ResponseVO2.setReqUserID("A123XXX789");
    ivr9014ResponseVO2.setRealCardNo("1234XXXXXXXX3456");
    ivr9014ResponseMap.put(ivr9014ResponseVO2.getReqUserID() + ivr9014ResponseVO2.getMessageRecNo(),
        ivr9014ResponseVO2);

    IVR9014ResponseVO ivr9014ResponseVO3 = new IVR9014ResponseVO();
    ivr9014ResponseVO3.setTxnID("IVR9014");
    ivr9014ResponseVO3.setUnitCD("0000");
    ivr9014ResponseVO3.setMessageRecNo("0003");
    ivr9014ResponseVO3.setReqUserID("A123XXX789");
    ivr9014ResponseVO3.setRealCardNo("4321XXXXXXXX6543");
    ivr9014ResponseMap.put(ivr9014ResponseVO3.getReqUserID() + ivr9014ResponseVO3.getMessageRecNo(),
        ivr9014ResponseVO3);

    IVR9014ResponseVO ivr9014ResponseVO4 = new IVR9014ResponseVO();
    ivr9014ResponseVO4.setTxnID("IVR9014");
    ivr9014ResponseVO4.setUnitCD("0000");
    ivr9014ResponseVO4.setMessageRecNo("0001");
    ivr9014ResponseVO4.setReqUserID("B987XXX321");
    ivr9014ResponseVO4.setRealCardNo("9876XXXXXXXX3456");
    ivr9014ResponseMap.put(ivr9014ResponseVO4.getReqUserID() + ivr9014ResponseVO4.getMessageRecNo(),
        ivr9014ResponseVO4);

    IVR9014ResponseVO ivr9014ResponseVO5 = new IVR9014ResponseVO();
    ivr9014ResponseVO5.setTxnID("IVR9014");
    ivr9014ResponseVO5.setUnitCD("0000");
    ivr9014ResponseVO5.setMessageRecNo("0002");
    ivr9014ResponseVO5.setReqUserID("B987XXX321");
    ivr9014ResponseVO5.setRealCardNo("6789XXXXXXXX6543");
    ivr9014ResponseMap.put(ivr9014ResponseVO5.getReqUserID() + ivr9014ResponseVO5.getMessageRecNo(),
        ivr9014ResponseVO5);

    IVR9014ResponseVO ivr9014ResponseVO6 = new IVR9014ResponseVO();
    ivr9014ResponseVO6.setTxnID("IVR9014");
    ivr9014ResponseVO6.setUnitCD("0000");
    ivr9014ResponseVO6.setMessageRecNo("0003");
    ivr9014ResponseVO6.setReqUserID("B987XXX321");
    ivr9014ResponseVO6.setRealCardNo("9876XXXXXXXX3456");
    ivr9014ResponseMap.put(ivr9014ResponseVO6.getReqUserID() + ivr9014ResponseVO6.getMessageRecNo(),
        ivr9014ResponseVO6);

    IVR9014ResponseVO ivr9014ResponseVO7 = new IVR9014ResponseVO();
    ivr9014ResponseVO7.setTxnID("IVR9014");
    ivr9014ResponseVO7.setUnitCD("0000");
    ivr9014ResponseVO7.setMessageRecNo("0004");
    ivr9014ResponseVO7.setReqUserID("B987XXX321");
    ivr9014ResponseVO7.setRealCardNo("6789XXXXXXXX6543");
    ivr9014ResponseMap.put(ivr9014ResponseVO7.getReqUserID() + ivr9014ResponseVO7.getMessageRecNo(),
        ivr9014ResponseVO7);
  }

  public IVR9013ResponseVO ivr9013(Exchange exchange) {
    IVR9013ResponseVO ivr9013ResponseVO = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonString = objectMapper.writeValueAsString(exchange.getIn().getBody());

      log.info("input: {}", jsonString);
      IVR9013RequestVO ivr9013RequestVO =
          objectMapper.readValue(jsonString, IVR9013RequestVO.class);
      
      ivr9013ResponseVO = processIntReq9013(ivr9013RequestVO);
    } catch (Exception e) {
      log.error("error.", e);
    }
    
    return ivr9013ResponseVO;
  }

  public IVR9014ResponseVO ivr9014(Exchange exchange) {
    IVR9014ResponseVO ivr9014ResponseVO = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonString = objectMapper.writeValueAsString(exchange.getIn().getBody());
      
      log.info("input: {}", jsonString);
      IVR9014RequestVO ivr9014RequestVO =
          objectMapper.readValue(jsonString, IVR9014RequestVO.class);
      
      ivr9014ResponseVO = processIntReq9014(ivr9014RequestVO);
    } catch (Exception e) {
      log.error("error.", e);
    }    
    return ivr9014ResponseVO;
  }

  @Override
  public IVR9013ResponseVO processIntReq9013(IVR9013RequestVO input) {
    IVR9013ResponseVO ret = null;
    log.debug("processIntReq9013 {}", input.toString());
    ret = ivr9013ResponseMap.get(input.getId());
    if (null == ret) {
      ret = new IVR9013ResponseVO();
      ret.setTxID("IVR9013");
      ret.setUnitCD("9999");
    }
    return ret;
  }

  @Override
  public IVR9014ResponseVO processIntReq9014(IVR9014RequestVO input) {
    IVR9014ResponseVO ret = null;
    log.debug("processIntReq9014 {}", input.toString());
    ret =
        ivr9014ResponseMap.get(input.getId() + input.getSeq());
    if (null == ret) {
      ret = new IVR9014ResponseVO();
      ret.setTxnID("IVR9014");
      ret.setUnitCD("9999");
    }
    return ret;
  }

}

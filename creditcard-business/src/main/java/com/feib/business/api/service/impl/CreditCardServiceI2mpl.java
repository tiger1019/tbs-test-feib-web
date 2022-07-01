package com.feib.business.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.feib.business.api.adapter.Integration9013;
import com.feib.business.api.adapter.Integration9014;
import com.feib.business.api.entity.TransactionDetailsPurchaseVO;
import com.feib.business.api.entity.TransactionDetailsRequestVO;
import com.feib.business.api.entity.TransactionDetailsResponseVO;
import com.feib.business.api.service.CreditCardService;
import com.feib.integration.api.entity.IVR9013RequestVO;
import com.feib.integration.api.entity.IVR9013ResponseDetailVO;
import com.feib.integration.api.entity.IVR9013ResponseVO;
import com.feib.integration.api.entity.IVR9014RequestVO;
import com.feib.integration.api.entity.IVR9014ResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Service
public class CreditCardServiceI2mpl implements CreditCardService {

    static final int ADAPTER_TYPE_API = 0;
    static final int ADAPTER_TYPE_ASYNCQ = 1;
    static final int ADAPTER_TYPE_SYNCQ = 2;
  
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${integration.adapter-type:0}")
	private Integer adapterType;
	
	@Autowired
	Integration9013 int9013;
	
	@Autowired
    Integration9014 int9014;

	private IVR9013ResponseVO process9013(String userID) {
	  IVR9013RequestVO ivr9013RequestVO = new IVR9013RequestVO();
      ivr9013RequestVO.setId(userID);        
      IVR9013ResponseVO ivr9013ResponseVO = null;
      
      switch(adapterType) {       
        case ADAPTER_TYPE_ASYNCQ:
          //int9013.ByAsyncEvent(ivr9013RequestVO);           
          break;
        case ADAPTER_TYPE_SYNCQ:
          ivr9013ResponseVO = int9013.ByEvent(ivr9013RequestVO);
          break;
        case ADAPTER_TYPE_API:
        default:
          ivr9013ResponseVO = int9013.ByApi(ivr9013RequestVO);
      }
      
      return ivr9013ResponseVO;
	}
	
    private IVR9014ResponseVO process9014(String userID, String seq) {
      IVR9014RequestVO ivr9014RequestVO = new IVR9014RequestVO();
      ivr9014RequestVO.setId(userID);
      ivr9014RequestVO.setSeq(seq);
      IVR9014ResponseVO ivr9014ResponseVO = null;
      
      switch(adapterType) {        
        case ADAPTER_TYPE_ASYNCQ:
          //int9014.ByAsyncEvent(ivr9014RequestVO);                     
          break;
        case ADAPTER_TYPE_SYNCQ:
          ivr9014ResponseVO = int9014.ByEvent(ivr9014RequestVO);
          break;
        case ADAPTER_TYPE_API:
        default:
          ivr9014ResponseVO = int9014.ByApi(ivr9014RequestVO);
      }
      
      return ivr9014ResponseVO;
    }
   
    @Override
    public TransactionDetailsResponseVO queryTransactionDetails(TransactionDetailsRequestVO transactionDetailsRequestVO) {
      log.debug("queryTransactionDetails {}", adapterType);
      TransactionDetailsResponseVO transactionDetailsResponseVO = 
          new TransactionDetailsResponseVO();
      List<TransactionDetailsPurchaseVO> transactionDetailsPurchaseVOs = 
          new ArrayList<TransactionDetailsPurchaseVO>();

      IVR9013ResponseVO ivr9013ResponseVO = process9013(transactionDetailsRequestVO.getUserID());

      for (IVR9013ResponseDetailVO ivr9013ResponseDetailVO : ivr9013ResponseVO.getDetail()) {
        TransactionDetailsPurchaseVO transactionDetailsPurchaseVO =
            new TransactionDetailsPurchaseVO();
        transactionDetailsPurchaseVO.setDescription(ivr9013ResponseDetailVO.getMerchantName());
        transactionDetailsPurchaseVO
            .setLocalAmount(Double.valueOf(ivr9013ResponseDetailVO.getDestinatiionAmt()));
        transactionDetailsPurchaseVO.setPostingDate(ivr9013ResponseDetailVO.getAcquireDate());
        transactionDetailsPurchaseVO.setPurchaseDate(ivr9013ResponseDetailVO.getPurchaseDate());

        IVR9014ResponseVO ivr9014ResponseVO =
            process9014(transactionDetailsRequestVO.getUserID(), ivr9013ResponseDetailVO.getSeq());

        transactionDetailsPurchaseVO.setCardNo(ivr9014ResponseVO.getRealCardNo());
        
        transactionDetailsPurchaseVOs.add(transactionDetailsPurchaseVO);
      }
      transactionDetailsResponseVO.setPurchase(transactionDetailsPurchaseVOs);
      log.debug("queryTransactionDetails result {}", transactionDetailsResponseVO.toString());
      return transactionDetailsResponseVO;
    }

}

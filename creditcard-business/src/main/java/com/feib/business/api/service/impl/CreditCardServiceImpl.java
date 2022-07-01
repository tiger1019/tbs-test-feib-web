package com.feib.business.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.feib.business.api.entity.TransactionDetailsPurchaseVO;
import com.feib.business.api.entity.TransactionDetailsRequestVO;
import com.feib.business.api.entity.TransactionDetailsResponseVO;
import com.feib.business.api.service.CreditCardService;
import com.feib.integration.api.entity.IVR9013RequestVO;
import com.feib.integration.api.entity.IVR9013ResponseDetailVO;
import com.feib.integration.api.entity.IVR9013ResponseVO;
import com.feib.integration.api.entity.IVR9014RequestVO;
import com.feib.integration.api.entity.IVR9014ResponseVO;

@Component
public class CreditCardServiceImpl implements CreditCardService {

	private final static Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);

	@Autowired
	RestTemplate restTemplate;

	@Override
	public TransactionDetailsResponseVO queryTransactionDetails(TransactionDetailsRequestVO transactionDetailsRequestVO) {
		TransactionDetailsResponseVO transactionDetailsResponseVO = new TransactionDetailsResponseVO();
		List<TransactionDetailsPurchaseVO> transactionDetailsPurchaseVOs = new ArrayList<TransactionDetailsPurchaseVO>();

		IVR9013RequestVO ivr9013RequestVO = new IVR9013RequestVO();
		ivr9013RequestVO.setId(transactionDetailsRequestVO.getUserID());
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<IVR9013RequestVO> request = new HttpEntity<>(ivr9013RequestVO, headers);
		ResponseEntity<IVR9013ResponseVO> result = restTemplate.postForEntity("http://localhost:9090/integration/api/ivr9013", request, IVR9013ResponseVO.class);
		IVR9013ResponseVO ivr9013ResponseVO = result.getBody();
		logger.info(ivr9013ResponseVO.toString());

		for (IVR9013ResponseDetailVO ivr9013ResponseDetailVO : ivr9013ResponseVO.getDetail()) {
			TransactionDetailsPurchaseVO transactionDetailsPurchaseVO = new TransactionDetailsPurchaseVO();
			transactionDetailsPurchaseVO.setDescription(ivr9013ResponseDetailVO.getMerchantName());
			transactionDetailsPurchaseVO.setLocalAmount(Double.valueOf(ivr9013ResponseDetailVO.getDestinatiionAmt()));
			transactionDetailsPurchaseVO.setPostingDate(ivr9013ResponseDetailVO.getAcquireDate());
			transactionDetailsPurchaseVO.setPurchaseDate(ivr9013ResponseDetailVO.getPurchaseDate());

			IVR9014RequestVO ivr9014RequestVO = new IVR9014RequestVO();
			ivr9014RequestVO.setId(transactionDetailsRequestVO.getUserID());
			ivr9014RequestVO.setSeq(ivr9013ResponseDetailVO.getSeq());
			HttpEntity<IVR9014RequestVO> ivr9014Request = new HttpEntity<>(ivr9014RequestVO, headers);
			ResponseEntity<IVR9014ResponseVO> ivr9014Result = restTemplate.postForEntity("http://localhost:9090/integration/api/ivr9014", ivr9014Request, IVR9014ResponseVO.class);
			IVR9014ResponseVO ivr9014ResponseVO = ivr9014Result.getBody();
			transactionDetailsPurchaseVO.setCardNo(ivr9014ResponseVO.getRealCardNo());
			transactionDetailsPurchaseVOs.add(transactionDetailsPurchaseVO);
		}
		transactionDetailsResponseVO.setPurchase(transactionDetailsPurchaseVOs);
		return transactionDetailsResponseVO;
	}

}

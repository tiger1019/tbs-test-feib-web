package com.feib.business.api.service;

import org.springframework.stereotype.Component;

import com.feib.business.api.entity.TransactionDetailsRequestVO;
import com.feib.business.api.entity.TransactionDetailsResponseVO;

@Component
public interface CreditCardService {

	TransactionDetailsResponseVO queryTransactionDetails(TransactionDetailsRequestVO transactionDetailsRequestVO);

}

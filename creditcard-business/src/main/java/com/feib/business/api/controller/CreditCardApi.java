package com.feib.business.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feib.business.api.entity.TransactionDetailsRequestVO;
import com.feib.business.api.entity.TransactionDetailsResponseVO;

@Api(tags="CreditCard", produces=MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/api")
public interface CreditCardApi {
  
  @ApiOperation(value = "查詢信用卡帳單交易明細", notes = "查詢信用卡帳單交易明細")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
  @PostMapping(value = "/transactionDetails", consumes = "application/json", produces = "application/json")
  public TransactionDetailsResponseVO queryTransactionDetails(@RequestBody TransactionDetailsRequestVO transactionDetailsRequestVO);
  
}

package com.feib.integration.api.controller;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feib.api.annotation.WebAdapter;
import com.feib.integration.api.entity.IVR9013ResponseVO;
import com.feib.integration.api.entity.IVR9014ResponseVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags="Integration", produces=MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/integration/bridge")
@RequiredArgsConstructor
@Slf4j
@WebAdapter
public class IntegrationBridgeController {
  
  @Autowired
  protected RestTemplate restTemplate;
  
  private final ObjectMapper objectMapper = new ObjectMapper();
  
  private <I,O> O postApi(String apiUrl, I vo, Class<O> returnClass) {
    log.debug(">>>PostApi {}", vo.toString());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    String req = null;
    try {
      
      req = objectMapper.writeValueAsString(vo);
    } catch (JsonProcessingException e) {
      log.error("Convert json error ", e);
      throw new IllegalStateException("Convert Fail"); 
    }
    log.debug("Req = {}", req);
    HttpEntity<String> request = new HttpEntity<String>(req, headers);
    
    O rep = restTemplate.postForObject(apiUrl, request, returnClass);
    log.debug("Rep = {}", rep);
    
    return rep;
  }

  @ApiOperation(value = "IVR9013 Bridge", notes = "IVR9013 Bridge")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
  @PostMapping(value = "/ivr9013", consumes = "application/json", produces = "application/json")
  public IVR9013ResponseVO ivr9013Bridge(
      @ApiParam(value = "id", required = true) 
      @RequestBody Ivr9013Req id) {    
    return postApi("http://localhost:9090//integration/api/ivr9013", id, IVR9013ResponseVO.class);
  }

  @ApiOperation(value = "IVR9014 Bridge", notes = "IVR9014 Bridge")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
  @PostMapping(value = "/ivr9014", consumes = "application/json", produces = "application/json")
  public IVR9014ResponseVO ivr9014Bridge(
      @ApiParam(value = "data", required = true) 
      @RequestBody Ivr9014Req data) {
    return postApi("http://localhost:9090//integration/api/ivr9014", data, IVR9014ResponseVO.class);
  }
  
  @Data
  public static class Ivr9013Req implements Serializable {
    private static final long serialVersionUID = -3858708070732065116L;    
    @ApiModelProperty(notes = "id", example = "A123XXX789")
    String id;

  }

  @Data
  public static class Ivr9014Req implements Serializable {
    private static final long serialVersionUID = -2999332575461636076L;
    public Ivr9014Req() {}
    @ApiModelProperty(notes = "id", example = "A123XXX789")
    String id;
    @ApiModelProperty(notes = "seq", example = "0001")
    String seq;
  }
}

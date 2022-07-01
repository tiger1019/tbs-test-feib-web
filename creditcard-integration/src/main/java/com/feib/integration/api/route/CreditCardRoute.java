package com.feib.integration.api.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import com.feib.integration.api.entity.IVR9013ResponseVO;
import com.feib.integration.api.entity.IVR9014ResponseVO;

@Component
public class CreditCardRoute extends RouteBuilder {

  //@formatter:off
  @Override
  public void configure() throws Exception {
    restConfiguration().component("netty-http").port(9090).bindingMode(RestBindingMode.auto)
        .dataFormatProperty("prettyPrint", "true")
        .apiContextPath("/integration/api-doc")
        .apiVendorExtension(true)
        .apiProperty("api.title", "Integeration Service API")
        .apiProperty("api.version", "1.0.0")
        .apiProperty("cors", "true");

    rest().description("Integeration Service").path("/integration/api").consumes("application/json")
        .produces("application/json")
        .post("/ivr9013")
        .description("IVR9013 Service.")
        .outType(IVR9013ResponseVO.class)
        .param().name("id").type(RestParamType.body).description("The consumer id.").dataType("string").endParam()
        .to("bean:creditCardProcessor?method=ivr9013")
        .post("/ivr9014")
        .description("IVR9014 Service.")
        .outType(IVR9014ResponseVO.class)
        .param().name("id").type(RestParamType.body).description("The consumer id.").dataType("string").endParam()
        .param().name("seq").type(RestParamType.body).description("The detail sequence number.").dataType("string").endParam()
        .to("bean:creditCardProcessor?method=ivr9014");
    
    // OK
//    from("rabbitmq:ex?exchangeType=headers&queue=int9013ReqQueue")
//        .id("int9013ReqQueue")
//        .description("RabbitMQ 9013")
//        .outputType(IVR9013ResponseVO.class)
//        .to("bean:creditCardProcessor?method=ivr9013")
//        ;
    //from("direct:startQueuePoint").log("From RabbitMQ: ${body}");
  }
  //@formatter:on

}

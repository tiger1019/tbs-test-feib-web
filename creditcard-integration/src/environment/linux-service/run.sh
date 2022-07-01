#!/bin/bash

#cd /opt/creditcard-integration
/c/java/jdk-15.0.1/bin/java \
  -cp conf:lib/* \
  -Dspring.application.admin.enabled=false \
  -Dfile.encoding=UTF8 \
  -Djava.awt.headless=true \
	-Djava.security.egd=file:/dev/urandom \
  -Dlogger.file=conf/logback.xml \
	-Dloader.main=com.feib.integration.api.FeibCreditCardIntegrationServiceApplication \
  -Dspring.profiles.active=dev \
  -Xms256m \
  -Xmx1024m \
  -XX:+HeapDumpOnOutOfMemoryError \
  -XX:HeapDumpPath=/tmp \
	org.springframework.boot.loader.PropertiesLauncher

#/c/java/jdk-15.0.1/bin/java \
#    -cp "conf:lib/*" \
#    -Dspring.application.admin.enabled=false \
#    -Dfile.encoding=UTF8 \
#    -Djava.awt.headless=true \
#	  -Djava.security.egd=file:/dev/urandom \
#    -Dlogger.file=conf/logback.xml \
#    -Dspring.profiles.active=dev \
#    -Xms256m \
#    -Xmx1024m \
#    -XX:+HeapDumpOnOutOfMemoryError \
#    -XX:HeapDumpPath=/tmp \
#	com.feib.integration.api.FeibCreditCardIntegrationServiceApplication
  
#/c/java/jdk-15.0.1/bin/java -jar ./lib/creditcard-integration-0.0.1.jar --spring.profiles.active=dev

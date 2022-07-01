java \
    -cp conf:lib/* \
    -javaagent:lib/elastic-apm-agent-1.29.0.jar \
    -Delastic.apm.service_name=creditcard-business \
    -Delastic.apm.server_urls=http://10.5.0.17:8200 \
    -Delastic.apm.secret_token= \
    -Delastic.apm.environment=production \
    -Delastic.apm.application_packages=com.feib \
    -Dspring.application.admin.enabled=false \
    -Dfile.encoding=UTF8 \
    -Djava.awt.headless=true \
    -Dlogger.file=conf/logback.xml \
    -Dloader.main=com.feib.business.api.FeibCreditCardBusinessServiceApplication \
    -Dspring.profiles.active=dev \
    -Xms256m \
    -Xmx1024m \
    -XX:+HeapDumpOnOutOfMemoryError \
    org.springframework.boot.loader.PropertiesLauncher

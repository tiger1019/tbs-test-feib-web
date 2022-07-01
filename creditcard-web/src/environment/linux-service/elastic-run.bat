
C:\java\jdk-15.0.1\bin\java ^
    -javaagent:lib/elastic-apm-agent-1.29.0.jar ^
    -Delastic.apm.service_name=creditcard-web ^
    -Delastic.apm.server_urls=http://127.0.0.1:8200 ^
    -Delastic.apm.secret_token= ^
    -Delastic.apm.environment=production ^
    -Delastic.apm.application_packages=com.feib ^
    -cp conf;lib/* ^
    -Dspring.application.admin.enabled=false ^
    -Dfile.encoding=UTF8 ^
    -Djava.awt.headless=true ^
    -Dlogger.file=conf/logback.xml ^
    -Dloader.main=com.feib.business.web.WebApp ^
    -Dspring.profiles.active=dev ^
    -Xms256m ^
    -Xmx1024m ^
    -XX:+HeapDumpOnOutOfMemoryError ^
    org.springframework.boot.loader.PropertiesLauncher

## Swagger API UI
http://localhost:9070/swagger-ui/index.html

## 程式架構
主要包括Framework & Driver層、Interface Adapter層、Use Case層與Entity層

### package 結構

```
project
  application
    {actual java frameworks}
    framework config
    framework controller
  adapter
    controller
      model
    encoder
    generator
    repository
  config
    {export usecase object for java frameworks}    
  domain
    entity
  usecase
    exception
    port
    validator
```
  
## 服務狀態

http://localhost:9070/business/actuator/health/liveness

http://localhost:9070/business/actuator/health/readiness

ApplicationEventPublisher eventPublisher;
AvailabilityChangeEvent.publish(eventPublisher, this, ReadinessState.ACCEPTING_TRAFFIC);
ApplicationAvailability availability;
LivenessState livenessState = availability.getLivenessState();
ReadinessState readinessState = availability.getReadinessState();



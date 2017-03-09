Basic example demonstrating Twitter to REST API with data mapping in between.

NOTE: This git repo ignores application.properties because that's where I have all my login info.  You will want to create your own with the following settings:

```
logging.config=classpath:logback.xml

# the options from org.apache.camel.spring.boot.CamelConfigurationProperties can be configured here
camel.springboot.name=MyCamel

# lets listen on all ports to ensure we can be invoked from the pod IP
server.address=0.0.0.0
management.address=0.0.0.0

# lets use a different management port in case you need to listen to HTTP requests on 8080
management.port=8081

# disable all management endpoints except health
endpoints.enabled = false
endpoints.health.enabled = true

# password info
twitter.consumer.key=[ENTER YOUR INFO HERE]
twitter.consumer.secret=[ENTER YOUR INFO HERE]
twitter.access.token=[ENTER YOUR INFO HERE]
twitter.access.token.secret=[ENTER YOUR INFO HERE]

cxf.path=/services/trader
```

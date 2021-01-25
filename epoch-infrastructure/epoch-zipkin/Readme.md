### epoch-zipkin

Zipkin 是一个开放源代码分布式的跟踪系统，每个服务向zipkin报告计时数据，zipkin会根据调用关系通过Zipkin UI生成依赖关系图。

### 如何引入
1. 搭建zipkin server

在epoch中我们采用docker-compose进行部署
 
docker-compose.yml
```
version: '3'

services:
  zipkin:
    image: openzipkin/zipkin
    container_name: zipkin
    environment:
      - STORAGE_TYPE=elasticsearch
      # Point the zipkin at the storage backend
      - ES_HOSTS=localhost:9200
      # Uncomment to see requests to and from elasticsearch
      # - ES_HTTP_LOGGING=BODY
    ports:
      # Port used for the Zipkin UI and HTTP Api
      - 9411:9411
      # Uncomment if you set SCRIBE_ENABLED=true
      # - 9410:9410
    network_mode: "host"
``` 

2 对需要进行追踪的服务进行配置

pom.xml
```
        <!-- zipkin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```
application.yml
```
spring:
  zipkin:
    base-url: http://192.168.1.102:9411
```

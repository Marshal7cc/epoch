### epoch-admin

Spring Boot Admin 是一个管理和监控 Spring Boot 应用程序的开源软件，每个应用都认为是一个客户端，
通过 HTTP 或者使用 Eureka 注册到 admin server 中进行展示，Spring Boot Admin UI 部分使用 Vue.js 将数据展示在前端。

### 如何引入
1. pom.xml
```
        <!-- spring-boot-admin-client -->
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-client</artifactId>
            <version>${springboot-admin.version}</version>
        </dependency>
```
2. application.yml
```
spring:
  boot:
    admin:
      client:
        url: ${admin.url:http://localhost:8090}
        username: epoch
        password: epoch1234!
        instance:
          prefer-ip: true
```

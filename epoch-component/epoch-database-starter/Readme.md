### epoch-database-starter

epoch-database-starter提供了标准的数据库引入，提供了一些标准化机制。

### 如何引入
pom.xml
```
        <dependency>
            <groupId>com.marshal</groupId>
            <artifactId>epoch-database-starter</artifactId>
            <version>${epoch.version}</version>
        </dependency>
```

application.yml
```
# epoch-database-starter配置
spring:
  # 数据源
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://${mysql.url:localhost:3306}/epoch?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8
    username: root
    password: 123456

# mybatis配置
mybatis:
  mapper-locations: classpath*:mapper/*.xml
#  configuration:
#    jdbc-type-for-null: null

# 通用mapper配置
mapper:
  mappers: tk.mybatis.mapper.common.Mapper
  not-empty: false
  identity: mysql

# pageHelper插件
pagehelper:
  helper-dialect: mysql
  reasonable: true
  support-methods-arguments: true
  params: count=countSql
```



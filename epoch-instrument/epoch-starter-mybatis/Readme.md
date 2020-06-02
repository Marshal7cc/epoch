### epoch-starter-mybatis

epoch-starter-mybatis提供了符合epoch标准的mybatis引入，提供了一些标准化机制。

### 如何引入
pom.xml
```
        <dependency>
            <groupId>com.marshal</groupId>
            <artifactId>epoch-starter-mybatis</artifactId>
            <version>${epoch.version}</version>
        </dependency>
```

application.yml
```
# epoch-starter-mybatis配置
spring:
  # 数据源
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${url}?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8
    username: ${username}
    password: ${pwd}

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



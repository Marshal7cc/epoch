## Introduction
### 1. 简介
epoch-starter-mybatis提供了符合epoch标准的mybatis引入，提供了一些标准化机制。

### 2. 组件坐标
```
<dependency>
    <groupId>org.epoch.starter</groupId>
    <artifactId>epoch-starter-mybatis</artifactId>
    <version>${epoch-starter.version}</version>
</dependency>
```

### 3. 接入配置
```
# epoch-starter-mybatis配置
spring:
  # 数据源
  datasource:
    driver-class-name: ${className}
    url: ${url}
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



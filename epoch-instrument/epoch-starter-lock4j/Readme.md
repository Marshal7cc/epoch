### epoch-starter-lock4j

epoch-lock4j-starter提供了标准的分布式锁实现，目前只提供了一种实现方式：

1.Redisson

### 如何引入
pom.xml
```
        <dependency>
            <groupId>com.marshal</groupId>
            <artifactId>epoch-starter-lock4j</artifactId>
            <version>${epoch.version}</version>
        </dependency>
```

application.yml
```
# epoch-lock4j-starter配置
epoch:
  lock4j:
    type: redisson
```

分布式锁的使用：
1.直接方法加上注解
```
@DistributedLock
```

2.手动调用DistributedLock

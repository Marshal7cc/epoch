### epoch-starter-lock

epoch-starter-lock提供了标准的分布式锁实现，目前只提供了一种实现方式：

1.Redisson

### 如何引入

pom.xml

```
        <dependency>
            <groupId>org.epoch.starter</groupId>
            <artifactId>epoch-starter-lock</artifactId>
            <version>${epoch-start-lock.version}</version>
        </dependency>
```

application.yml

```
# epoch lock
epoch:
  lock:
    single-server:
      address: localhost
      port: 6379
```

分布式锁的使用： 1.直接方法加上注解

```
@Lock
public void lockDemo(){
    // ...
}
```

2.手动调用

```
lockService.lock(lockInfo);
```

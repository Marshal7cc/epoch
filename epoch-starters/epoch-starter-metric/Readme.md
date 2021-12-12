### epoch-starter-metric

Metric信息收集组件，收集的信息可以集合Prometheus等平台进行统一的监控。

### 如何引入

1 pom.xml

```
        <dependency>
            <groupId>org.epoch.starter</groupId>
            <artifactId>epoch-starter-metric</artifactId>
            <version>${epoch-start-metric.version}</version>
        </dependency>
```

2 application.yml

```
# epoch metric
epoch:
  metric:
    enabled: true
```

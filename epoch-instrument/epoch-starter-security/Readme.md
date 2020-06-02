### epoch-starter-security

epoch-starter-security提供了标准的权限机制。

### 如何引入
pom.xml
```
        <dependency>
            <groupId>com.marshal</groupId>
            <artifactId>epoch-starter-security</artifactId>
            <version>${epoch.version}</version>
        </dependency>
```

application.yml
```
# epoch-starter-security配置
epoch:
  # 安全配置
  security:
    white-list:/query,/select
```

application.java
```
@EnableAuth
```

### 添加自定义权限策略

继承PermissionVoter并实现其vote方法
```
public class ResourceVoter extends PermissionVoter {

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> collection) {

    }
}
```

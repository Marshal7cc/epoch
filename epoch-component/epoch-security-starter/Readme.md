### epoch-security-starter

epoch-security-starter提供了标准的权限机制。

### 如何引入
pom.xml
```
        <dependency>
            <groupId>com.marshal</groupId>
            <artifactId>epoch-security-starter</artifactId>
            <version>${epoch.version}</version>
        </dependency>
```

application.yml
```
# epoch-database-starter配置
epoch:
  # 安全配置
  security:
    white-list:/query
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

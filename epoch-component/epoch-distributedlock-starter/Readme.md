### epoch-distributedlock-starter

epoch-distributedlock-starter提供了标准的分布式锁实现，目前只提供了一种实现方式：

1.Redis

### 如何引入
pom.xml
```
        <dependency>
            <groupId>com.marshal</groupId>
            <artifactId>epoch-distributedlock-starter</artifactId>
            <version>${epoch.version}</version>
        </dependency>
```

application.yml
```
# epoch-distributedlock-starter配置
epoch:
  distributed-lock:
    type: redis
    jedis:
      host: localhost
```

分布式锁的使用(代码粗糙望谅解)：
```
@SpringBootTest
class TestApplicationTests {

    @Autowired
    private DistributedLocker distributedLocker;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            Thread.sleep(1000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        secKill();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(50000);
    }

    public void secKill() throws InterruptedException {
        boolean flag = distributedLocker.tryLock("sec_kill", 5000);
        if (flag) {
            System.out.println("successsuccesssuccesssuccesssuccesssuccesssuccess");
            Thread.sleep(3000L);
            System.out.println("开始解除锁。。。");
            distributedLocker.release("sec_kill");
        } else {
            System.out.println("fail");
        }
    }

}
```

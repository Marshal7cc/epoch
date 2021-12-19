package org.epoch.security;

import org.epoch.starter.security.annotation.EpochAuthorizationServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Marshal
 * @date 2020/1/15
 *
 */
@MapperScan("org.epoch.**.mapper")
@EpochAuthorizationServer
@SpringBootApplication
public class OAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }

}

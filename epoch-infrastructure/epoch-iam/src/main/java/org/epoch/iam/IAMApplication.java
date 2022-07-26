package org.epoch.iam;

import org.epoch.boot.autoconfigure.EpochApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

/**
 * @author Marshal
 * @date 2020/1/12
 */
@MapperScan("org.epoch.**.mapper")
@EpochApplication
public class IAMApplication {

    public static void main(String[] args) {
        SpringApplication.run(IAMApplication.class, args);
    }

}

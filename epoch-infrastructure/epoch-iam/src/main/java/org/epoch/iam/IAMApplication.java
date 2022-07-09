package org.epoch.iam;

import org.epoch.web.annotation.EpochApplication;
import org.springframework.boot.SpringApplication;
import tk.mybatis.spring.annotation.MapperScan;

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

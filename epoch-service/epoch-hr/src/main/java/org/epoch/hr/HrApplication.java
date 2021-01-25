package org.epoch.hr;

import org.epoch.web.annotation.EpochApplication;
import org.springframework.boot.SpringApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Marshal
 * @date 2020/1/18
 *
 */
@MapperScan("org.epoch.**.mapper")
@EpochApplication
public class HrApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

}

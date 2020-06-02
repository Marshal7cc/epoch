package com.marshal.epoch.system;

import com.marshal.epoch.web.annotation.EpochApplication;
import org.springframework.boot.SpringApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Marshal
 * @date 2020/1/12
 *
 */
@MapperScan("com.marshal.epoch.**.mapper")
@EpochApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}

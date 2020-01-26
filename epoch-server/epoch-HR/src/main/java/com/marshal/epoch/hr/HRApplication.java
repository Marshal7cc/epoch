package com.marshal.epoch.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @auth: Marshal
 * @date: 2020/1/18
 * @desc:
 */
@ComponentScan("com.marshal.epoch.**")
@MapperScan("com.marshal.epoch.**.mapper")
@SpringBootApplication
public class HRApplication {

    public static void main(String[] args) {
        SpringApplication.run(HRApplication.class, args);
    }

}

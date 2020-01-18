package com.marshal.epoch.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @auth: Marshal
 * @date: 2020/1/12
 * @desc:
 */
@ComponentScan("com.marshal.epoch.**")
@MapperScan("com.marshal.epoch.**.mapper")
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}

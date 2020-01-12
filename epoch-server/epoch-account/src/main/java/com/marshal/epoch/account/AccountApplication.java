package com.marshal.epoch.account;

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
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}

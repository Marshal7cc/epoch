package org.epoch.starter.mybatis;

import org.epoch.starter.mybatis.properties.MybatisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Marshal
 * @date 2020/4/20
 */
@MapperScan("org.epoch.**.mapper")
@ComponentScan(basePackages = "org.epoch.mybatis.**")
@EnableConfigurationProperties({MybatisProperties.class})
public class MybatisAutoConfiguration {

}

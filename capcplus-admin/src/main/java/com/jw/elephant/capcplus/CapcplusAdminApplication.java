package com.jw.elephant.capcplus;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 大象平台启动类
 * @author zhangjie
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.jw.elephant.capcplus.mapper")
@ServletComponentScan("com.jw.elephant.capcplus.filter")
@Slf4j
public class CapcplusAdminApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CapcplusAdminApplication.class, args);
    }
}

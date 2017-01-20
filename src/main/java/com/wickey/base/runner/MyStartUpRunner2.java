package com.wickey.base.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by fatboyliang on 2016/8/17.
 */
@Component
@Order(value = 1)
public class MyStartUpRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作222222<<<<<<<<<<<<<");
    }
}

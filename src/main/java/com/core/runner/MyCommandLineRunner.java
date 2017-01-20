package com.core.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fatboyliang on 2016/8/21.
 */
@Configuration
public class MyCommandLineRunner implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception {
        System.out.println("it's running");
    }
}

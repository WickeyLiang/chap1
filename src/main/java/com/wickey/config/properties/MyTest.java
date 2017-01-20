package com.wickey.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fatboyliang on 2016/8/21.
 */
@ConfigurationProperties(prefix = "wickey",locations = "classpath:config/mytest.properties")
public class MyTest {
    private String name;
    private String sex;
    private int random;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }
}

package com.wickey.web;

import com.wickey.config.properties.MyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fatboyliang on 2016/8/13.
 */
@RestController
public class HelloController {

    // 在Java类中创建 logger 实例
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected MyTest myTest;

    @RequestMapping("/hello")
    public String index(){
        logger.info(myTest.getName());
        logger.debug(String.valueOf(myTest.getRandom()));
        return "hello world "+myTest.getRandom();
    }
}

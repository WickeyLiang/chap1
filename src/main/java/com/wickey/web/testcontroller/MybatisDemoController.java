package com.wickey.web.testcontroller;

import com.github.pagehelper.PageHelper;
import com.wickey.bean.testbean.MybatisDemo;
import com.wickey.service.testservice.MybatisDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fatboyliang on 2016/10/5.
 */
@RestController
@RequestMapping("/mybatis")
public class MybatisDemoController {

    @Autowired
    private MybatisDemoService mybatisDemoService;

    @RequestMapping("/likeName")
    public List<MybatisDemo> likeName(String name){
        PageHelper.startPage(1,1);
        return mybatisDemoService.likeName(name);
    }

}

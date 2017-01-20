package com.wickey.web.testcontroller;

import com.wickey.bean.testbean.TestBean;
import com.wickey.service.testservice.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fatboyliang on 2016/8/22.
 */
@Controller
@RequestMapping("/redistest")
public class RedisTestController {

    @Autowired
    TestService testService;

    @RequestMapping(value= "/test")
    @ResponseBody
    public String test(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id").toString());
        TestBean loaded = testService.findById(1);
        System.out.println("loaded="+loaded);
        TestBean cached = testService.findById(1);
        System.out.println("cached="+cached);
        loaded = testService.findById(2);
        System.out.println("loaded2="+loaded);
        return "ok";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id){
        testService.deleteFromCache(id);
        return "ok";
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(){
        testService.test();
        System.out.println("testService.test()");
        return "ok";
    }

}

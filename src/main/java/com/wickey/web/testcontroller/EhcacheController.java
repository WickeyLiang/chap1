package com.wickey.web.testcontroller;

import com.wickey.bean.testbean.TestBean;
import com.wickey.service.testservice.TestService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fatboyliang on 2016/9/4.
 */
@RestController
@RequestMapping("/ehcache")
public class EhcacheController {

    @Resource
    private TestService testService;

    @RequestMapping("/test")
    public String test(){

        //存入两条数据.
        TestBean demoInfo = new TestBean();
        demoInfo.setName("张三");
        demoInfo.setPwd("123456");
        TestBean demoInfo2 = testService.save(demoInfo);

        //不走缓存.
        System.out.println(testService.findById(demoInfo2.getId()));
        //走缓存.
        System.out.println(testService.findById(demoInfo2.getId()));


        demoInfo = new TestBean();
        demoInfo.setName("李四");
        demoInfo.setPwd("123456");
        TestBean demoInfo3 = testService.save(demoInfo);

        //不走缓存.
        System.out.println(testService.findById(demoInfo3.getId()));
        //走缓存.
        System.out.println(testService.findById(demoInfo3.getId()));

        System.out.println("============修改数据=====================");
        //修改数据.
        TestBean updated = new TestBean();
        updated.setName("李四-updated");
        updated.setPwd("123456");
        updated.setId(demoInfo3.getId());
        try {
            System.out.println(testService.update(updated));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        //不走缓存.
        System.out.println(testService.findById(updated.getId()));

        return "ok";
    }


    @RequestMapping("/get")
    public String get(HttpServletRequest request) throws Exception{
        long id = Long.parseLong(request.getParameter("id").toString());

        System.out.println(testService.findById(id));
        return "ok";
    }

}

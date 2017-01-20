package com.wickey.web.testcontroller;

import com.wickey.bean.testbean.Demo;
import com.wickey.demo.Shanhy;
import com.wickey.service.testservice.DemoService;
import com.wickey.task.testtask.MyTestTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by fatboyliang on 2016/8/13.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    /*@Resource(name = "shanhyA")
    private Shanhy shanhyA;*/

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("shanhyA")
    private Shanhy shanhyA;

    @Resource
    private DemoService demoService;

    @Resource
    private MyTestTask myTestTask;

    @RequestMapping("/getDemo")
    public Demo getDemo(){
        Demo demo = new Demo();
        demo.setId(1032343243);
        demo.setId(123123);
        demo.setName("wickey");
        shanhyA.display();
        return demo;
    }

    //测试全局异常捕捉
    @RequestMapping("/exceptionTest")
    public int exceptionTest(){
        return 100/0;
    }

    /**
     * 测试保存数据方法.
     * @return
     */
    @RequestMapping("/save")
    public String save(){
        Demo d = new Demo();
        d.setName("Angel");
        demoService.save(d);//保存数据.
        return"ok.Demo2Controller.save";
    }


    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    @RequestMapping("/getById")
    public Demo getById(long id){
        logger.info("id="+id);
        return demoService.getById(id);
    }


    @RequestMapping("/test1")
    public String test() throws Exception{
     for(Demo d:demoService.getList()){
        System.out.println(d);
     }
        for(Demo d:demoService.getListByDs1()){
            System.out.println(d);
        }
        return"ok";
    }


    @RequestMapping("/task1")
    public String task1() throws Exception{

        myTestTask.doTaskOne();
        myTestTask.doTaskTwo();
        myTestTask.doTaskThree();
        return "task1";
    }


}

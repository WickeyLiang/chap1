package com.wickey.web.testcontroller;

import com.wickey.queue.service.RabbitMQTest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fatboyliang on 2016/10/10.
 */
@RestController
@RequestMapping(value = "/queue")
public class MqTestController {

    /*@Autowired
    private RabbitMQTest rabbitMQTest;

    @RequestMapping("/test")
    public void TestMqApi(){
        rabbitMQTest.send();
    }*/


}

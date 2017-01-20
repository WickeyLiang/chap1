package com.wickey.web;

import com.wickey.DemoApplication;
import com.wickey.web.testcontroller.UserRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by fatboyliang on 2016/10/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class UserRestControllerTest {

    //模拟mvc对象类.
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup(){
       /*
        * MockMvcBuilders使用构建MockMvc对象.
        */
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testUserController() throws Exception{
        RequestBuilder request = null;
        //1. get 以下user列表，应该为空》

        //1、构建一个get请求.
        request = MockMvcRequestBuilders.get("/users");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("[\n" +
                        "\t{\n" +
                        "\t\t\"id\":2,\n" +
                        "\t\t\"name\":\"Wickey\"\n" +
                        "\t}\n" +
                        "]"))
        ;
        System.out.println("UserControllerTest.testUserController().get");

        // 2、post提交一个user
        request = MockMvcRequestBuilders.post("/users")
                .param("id","1")
                .param("name","linfeng")
                //.param("age","20")
        ;


        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("\"success\""));

        // 3、get获取user列表，应该有刚才插入的数据
        request = MockMvcRequestBuilders.get("/users");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("[\n" +
                "\t{\n" +
                "\t\t\"id\":1,\n" +
                "\t\t\"name\":\"linfeng\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\":2,\n" +
                "\t\t\"name\":\"Wickey\"\n" +
                "\t}\n" +
                "]"));


        // 4、put修改id为1的user
        request = MockMvcRequestBuilders.put("/users/1")
                .param("name", "linzexu")
                //.param("age", "30")
        ;
        mvc.perform(request)
                .andExpect(content().string("\"success\""));

        // 5、get一个id为1的user
        request = MockMvcRequestBuilders.get("/users/1");
        mvc.perform(request)
                .andExpect(content().string("{\n" +
                        "\t\"id\":1,\n" +
                        "\t\"name\":\"linzexu\"\n" +
                        "}"));



        // 6、del删除id为1的user
        request = MockMvcRequestBuilders.delete("/users/1");
        mvc.perform(request)
                .andExpect(content().string("\"success\""));

        // 7、get查一下user列表，应该为空
        request = MockMvcRequestBuilders.get("/users");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("[\n" +
                        "\t{\n" +
                        "\t\t\"id\":2,\n" +
                        "\t\t\"name\":\"Wickey\"\n" +
                        "\t}\n" +
                        "]"));

    }


}

package com.wickey.web.testcontroller;

import com.wickey.bean.testbean.Demo;
import com.wickey.service.testservice.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fatboyliang on 2016/10/6.
 */
@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private DemoService demoService;

    /**
     * 返回所有的用户.
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Demo> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<Demo> r = demoService.getList();
        return r;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Demo getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return demoService.getById(id);
    }


    /**
     * post 保存用户.
     * @param user
     * @return
     */
    @RequestMapping(value = "",method=RequestMethod.POST)
    public String postUser(@ModelAttribute Demo user){
        // 处理"/users/"的POST请求，用来创建User
        //@ModelAttribute User user
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        demoService.addDemo(user);
        return"success";
    }


    /**
     * 使用put 进行更新用户.
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id,@ModelAttribute Demo user){
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        Demo u = demoService.getById(id);
        u.setName(user.getName());
        demoService.save(u);
        return "success";
    }

    /**
     * 使用delete删除用户.
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        // url中的id可通过@PathVariable绑定到函数的参数中
        demoService.delById(id);
        return "success";
    }

}

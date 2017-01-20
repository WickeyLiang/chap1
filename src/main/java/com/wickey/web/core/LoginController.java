package com.wickey.web.core;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by fatboyliang on 2016/10/12.
 */

@RestController
public class LoginController {



    @RequestMapping({"/isAuth"})
    public ModelMap index(HttpServletRequest request){
        ModelMap resultMap = new ModelMap();
        Map<String,Object> dataMap = new HashedMap();
        dataMap.put("code","200");
        dataMap.put("msg","登录授权成功");
        dataMap.put("userInfo",request.getAttributeNames());
        System.out.println(dataMap);
        resultMap.addAttribute("data",dataMap);
        return resultMap;
    }



    @RequestMapping(value="/login",method= RequestMethod.POST)
    public ModelMap login(HttpServletRequest request) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        ModelMap resultMap = new ModelMap();
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        if("".equals(msg)){
            resultMap.put("code","200");
            resultMap.put("userInfo", request.getSession());
        }else{
            resultMap.put("code","500");
            resultMap.put("msg",msg);
        }

        return resultMap;
    }

    @RequestMapping(value = "/unAuth" ,method= RequestMethod.GET)
    public ModelMap unAuth(HttpServletRequest request) throws Exception{
        ModelMap resultMap = new ModelMap();
        Map<String,Object> dataMap = new HashedMap();
        dataMap.put("code","500");
        dataMap.put("msg","未经授权");
        resultMap.addAttribute("data",dataMap);
        return resultMap;
    }

    @RequestMapping(value = "/login" ,method= RequestMethod.GET)
    public ModelMap login() throws Exception{
        ModelMap resultMap = new ModelMap();
        Map<String,Object> dataMap = new HashedMap();
        dataMap.put("code","500");
        dataMap.put("msg","未做登录");
        resultMap.addAttribute("data",dataMap);
        return resultMap;
    }



}

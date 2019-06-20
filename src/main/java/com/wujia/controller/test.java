package com.wujia.controller;

import com.wujia.entity.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/test")
public class test {
    /**
     * 测试方法
     */
    @RequestMapping("/helloShiro")
    public String helloShiro(Users users,Model model){
        System.out.println("helloShiro");
        model.addAttribute("name","吾嘉");
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(users.getuName(),users.getuPsw());

        try {
            //无异常登录成功
            subject.login(token);
            return "/test";
        } catch (UnknownAccountException e) {
            //返回UnknownAccountException异常
            //e.printStackTrace();
            System.out.println("用户名不存在");
            return "/login";
        } catch (IncorrectCredentialsException e){
            //返回IncorrectCredentialsException异常
            System.out.println("密码错误");
            return "/login";
        }
    }

    @RequestMapping("/addTest")
    public String addTest(){
        return "/testAdd";
    }
    @RequestMapping("/hellotest")
    public String helloTest(){
        return "/login";
    }
}

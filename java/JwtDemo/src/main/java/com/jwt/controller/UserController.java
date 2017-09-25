package com.jwt.controller;

import com.jwt.define.ResultBean;
import com.jwt.define.User;
import com.jwt.service.impls.UserServiceImpl;
import com.jwt.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by lzp on 2017/9/17.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/userLogin")
    @ResponseBody
    public ResultBean login(@RequestBody User user){

        System.out.println("执行登录:"+user);
        ResultBean result=userService.login(user.getLoginname(),user.getLoginpwd());
//        System.out.println(result);
        return result;
    }
    @GetMapping("/getUserInfo/{loginname}")
    @ResponseBody
    public ResultBean getUserInfo(@PathVariable String loginname){
        System.out.println("获取用户信息");
        return userService.getUserInfo(loginname);
    }
}

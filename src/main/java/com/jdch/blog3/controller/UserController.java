package com.jdch.blog3.controller;

import com.jdch.blog3.entity.User;
import com.jdch.blog3.model.ResultModel;
import com.jdch.blog3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description:用户控制层
 *
 * @author Zoisitesky
 * @date 2020-11-01 22:03
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "login.action", method = RequestMethod.POST)
    public ResultModel<User> login(String username, String password){
        ResultModel<User> model = new ResultModel<>();
        boolean login = userService.login(username, password);
        if (login) {
            model.setCode(0);
            model.setMessage("登录成功");
        }else{
            model.setCode(1);
            model.setMessage("登录失败");
        }
        return model;
    }

    @RequestMapping(value = "user/index.action")
    public String indexHtml() {
        System.out.println("--------");
        return "redirect: /user/index.html";
    }
}

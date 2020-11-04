package com.jdch.blog3.controller;

import com.jdch.blog3.entity.User;
import com.jdch.blog3.model.ResultModel;
import com.jdch.blog3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public ResultModel<User> login(String username, String password, String remember, HttpServletRequest request){
        ResultModel<User> model = new ResultModel<>();
        boolean login = userService.login(username, password, remember, request);
        if (login) {
            model.setCode(0);
            model.setMessage("登录成功");
        }else{
            model.setCode(1);
            model.setMessage("登录失败");
        }
        return model;
    }

    @RequestMapping(value = "user/loginOut.action", method = RequestMethod.POST)
    public ResultModel<String> indexHtml(HttpServletRequest request) {
        ResultModel<String> model = new ResultModel<>();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        model.setCode(0);
        model.setMessage("登出成功");
        return model;
    }
}

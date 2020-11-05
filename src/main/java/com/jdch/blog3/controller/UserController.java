package com.jdch.blog3.controller;

import com.jdch.blog3.entity.User;
import com.jdch.blog3.model.ResultModel;
import com.jdch.blog3.service.UserService;
import com.jdch.blog3.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /*
     * @Description: 用户登录
     * @Param: [username, password, remember, request]
     * @return: com.jdch.blog3.model.ResultModel<com.jdch.blog3.entity.User>
     */
    @RequestMapping(value = "login.action", method = RequestMethod.POST)
    public ResultModel<User> login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        ResultModel<User> model = new ResultModel<>();
        String change = request.getParameter("change");
        //判断是否改变了密码框内容
        boolean flag = false;
        if ("1".equals(change)) {
            //取得cookie,判读是否有用户账号密码信息,如果没有则对密码进行加密
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("blog_remember".equals(cookie.getName())) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            password = MD5Util.encrypt(password);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean login = userService.login(user, request, response);
        if (login) {
            model.setCode(0);
            model.setMessage("登录成功");
        }else{
            model.setCode(1);
            model.setMessage("登录失败");
        }
        return model;
    }

    /*
     * @Description: 用户登出
     * @Param: [request]
     * @return: com.jdch.blog3.model.ResultModel<java.lang.String>
     */
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

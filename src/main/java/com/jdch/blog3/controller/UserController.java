package com.jdch.blog3.controller;

import com.jdch.blog3.entity.User;
import com.jdch.blog3.model.ResultModel;
import com.jdch.blog3.service.UserService;
import com.jdch.blog3.util.FileUtils;
import com.jdch.blog3.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResultModel<String> loginOut(HttpServletRequest request) {
        ResultModel<String> model = new ResultModel<>();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        model.setCode(0);
        model.setMessage("登出成功");
        return model;
    }

    /*
     * @Description: 修改用户信息
     * @Param: [user]
     * @return: com.jdch.blog3.model.ResultModel<java.lang.String>
     */
    @RequestMapping(value = "user/userUpdate.action", method = RequestMethod.POST)
    public ResultModel<User> update(@RequestParam("id") String id, @RequestParam("nickname") String nickname, @RequestParam("email") String email,
                       @RequestParam("phone") String phone, @RequestParam("summary") String summary, HttpServletRequest request,
                       @RequestParam("headIcon") MultipartFile headIcon, @RequestParam("wxCode") MultipartFile wxCode) {
        ResultModel<User> model = new ResultModel<>();
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSummary(summary);
        String upload = FileUtils.upload(request, wxCode, "/static/img/wxcode/");
        if (upload != null) {
            user.setWxCode(upload);
        }
        String upload1 = FileUtils.upload(request, headIcon, "/static/img/headicon/");
        if (upload1 != null) {
            user.setHeadIcon(upload1);
        }
        boolean update = userService.update(user, request);
        if (update) {
            model.setCode(0);
            model.setT(user);
            model.setMessage("修改成功");
        }else{
            model.setCode(1);
            model.setMessage("修改失败");
        }
        return model;
    }

    /*
     * @Description: 验证密码
     * @Param: [request]
     * @return: com.jdch.blog3.model.ResultModel<java.lang.String>
     */
    @RequestMapping(value = "user/checkPassword.action", method = RequestMethod.POST)
    public ResultModel<String> checkPassword(User user) {
        ResultModel<String> model = new ResultModel<>();
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        boolean flag = userService.checkPassword(user);
        if (flag) {
            model.setCode(0);
            model.setMessage("密码正确");
        }else{
            model.setCode(1);
            model.setMessage("密码错误");
        }
        return model;
    }

    /*
     * @Description: 修改密码
     * @Param: [user, request, response]
     * @return: com.jdch.blog3.model.ResultModel<java.lang.String>
     */
    @RequestMapping(value = "user/updatePassword.action", method = RequestMethod.POST)
    public ResultModel<String> updatePassword(User user, HttpServletRequest request, HttpServletResponse response) {
        ResultModel<String> model = new ResultModel<>();
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        boolean flag = userService.updatePassword(user);
        if (flag) {
            //密码修改成功移除账号密码cookie
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("blog_remember".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            model.setCode(0);
            model.setMessage("修改密码成功");
        }else{
            model.setCode(1);
            model.setMessage("修改密码失败");
        }
        return model;
    }
}

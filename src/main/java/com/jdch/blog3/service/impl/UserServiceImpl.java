package com.jdch.blog3.service.impl;

import com.jdch.blog3.entity.User;
import com.jdch.blog3.entity.UserExample;
import com.jdch.blog3.mapper.UserMapper;
import com.jdch.blog3.service.UserService;
import com.jdch.blog3.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * description:用户service实现类
 *
 * @author Zoisitesky
 * @date 2020-11-01 21:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String username, String password, String remember, HttpServletRequest request) {
        boolean flag = false;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users= userMapper.selectByExample(userExample);
        if (users.size() > 0) {
            User user1 =users.get(0);
            if(MD5Util.encrypt(password).equals(user1.getPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("user", user1);
                //判断是否勾选了记住密码 然后添加cookie
                if ("on".equals(remember)) {
                    Cookie cookie = new Cookie("blog_remember", user1.getUsername() + "@@@" + user1.getPassword());
                    cookie.setMaxAge(60*60*24*30);
                }
                flag = true;
            }
        }
        return flag;
    }
}

package com.jdch.blog3.service.impl;

import com.jdch.blog3.entity.User;
import com.jdch.blog3.entity.UserExample;
import com.jdch.blog3.mapper.UserMapper;
import com.jdch.blog3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public boolean login(User user, HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        List<User> users= userMapper.selectByExample(userExample);
        if (users.size() > 0) {
            User user1 =users.get(0);
            if(user.getPassword().equals(user1.getPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("user", user1);
                //判断是否勾选了记住密码 然后添加cookie,否则移除cookie
                String remember = request.getParameter("remember");
                if ("on".equals(remember)) {
                    Cookie cookie = new Cookie("blog_remember", user1.getUsername() + "@@@" + user1.getPassword());
                    cookie.setMaxAge(60*60*24*30);
                    response.addCookie(cookie);
                }else{
                    Cookie[] cookies = request.getCookies();
                    for (Cookie cookie : cookies) {
                        if ("blog_remember".equals(cookie.getName())) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean update(User user, HttpServletRequest request) {
        boolean flag = false;
        //修改用户信息,通过用户id获取新的用户信息
        int i = userMapper.updateByPrimaryKeySelective(user);
        User select = userMapper.selectByPrimaryKey(user.getId());
        if (i>0) {
            flag = true;
            HttpSession session = request.getSession();
            session.setAttribute("user", select);
        }
        return flag;
    }
}

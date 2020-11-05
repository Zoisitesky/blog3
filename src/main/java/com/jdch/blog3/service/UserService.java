package com.jdch.blog3.service;

import com.jdch.blog3.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description:用户service接口
 *
 * @author Zoisitesky
 * @date 2020-11-01 21:42
 */
public interface UserService {

    /*
     * @Description: 用户登录
     * @Param: [user, request, response]
     * @return: boolean
     */
    public boolean login(User user, HttpServletRequest request, HttpServletResponse response);
}

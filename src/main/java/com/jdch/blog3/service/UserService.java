package com.jdch.blog3.service;

import javax.servlet.http.HttpServletRequest;

/**
 * description:用户service接口
 *
 * @author Zoisitesky
 * @date 2020-11-01 21:42
 */
public interface UserService {

    /*
     * @Description: 用户登录
     * @Param: [username, password, remember, request]
     * @return: boolean
     */
    public boolean login(String username, String password, String remember, HttpServletRequest request);
}

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

    /*
     * @Description: 修改用户信息
     * @Param: [user, request]
     * @return: boolean
     */
    public boolean update(User user, HttpServletRequest request);

    /*
     * @Description: 验证用户密码
     * @Param: [user]
     * @return: boolean
     */
    public boolean checkPassword(User user);

    /*
     * @Description: 修改用户密码
     * @Param: [user]
     * @return: boolean
     */
    public boolean updatePassword(User user);
}

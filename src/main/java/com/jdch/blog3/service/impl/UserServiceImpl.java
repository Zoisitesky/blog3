package com.jdch.blog3.service.impl;

import com.jdch.blog3.entity.User;
import com.jdch.blog3.entity.UserExample;
import com.jdch.blog3.mapper.UserMapper;
import com.jdch.blog3.service.UserService;
import com.jdch.blog3.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean login(String username, String password) {
        boolean flag = false;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users= userMapper.selectByExample(userExample);
        if (users.size() > 0) {
            User user1 =users.get(0);
            if(MD5Util.encrypt(password).equals(user1.getPassword())){
                flag = true;
            }
        }
        return flag;
    }
}

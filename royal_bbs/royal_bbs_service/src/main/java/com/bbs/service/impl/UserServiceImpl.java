package com.bbs.service.impl;

import com.bbs.dao.UserDao;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 用户登录
     * @param userName
     * @param userPass
     * @return
     * @throws Exception
     */
    @Override
    public User login(String userName, String userPass) throws Exception {

        User user = userDao.login(userName, userPass);

        return user;
    }
}

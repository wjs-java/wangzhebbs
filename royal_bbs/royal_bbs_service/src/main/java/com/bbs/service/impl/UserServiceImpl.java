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

    /**
     * 查询所有用户数据
     * @return
     */

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }



    /**
     *
     * 根据用户名和密码查询用户
     * @param userName
     * @param userPass
     * @return
     */
    @Override
    public User findByUserNameAndPassword(String userName, String userPass) {
        User user = null;
        try {
            user = userDao.findByUserNameAndPassword(userName,userPass);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}

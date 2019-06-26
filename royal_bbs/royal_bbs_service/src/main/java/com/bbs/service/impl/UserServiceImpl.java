package com.bbs.service.impl;

import com.bbs.dao.UserDao;
import com.bbs.domain.Article;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import com.github.pagehelper.PageHelper;
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
     * 用户登录
     * @param userName
     * @param userPass
     * @return
     * @throws Exception
     */
    @Override
    public User findByUsernameAndUserPass(String userName, String userPass) throws Exception{
        User login = userDao.findByUsernameAndUserPass(userName, userPass);
        return login;
    }


    @Override
    public void updateLoginStatus( Integer loginStatus,Integer userId) {
        userDao.updateLoginStatus(loginStatus,userId);
    }

    /**
     * 保存用户注册信息的方法
     * @param user
     * @return
     */
    @Override
    public boolean saveRegister(User user) {
        User u = userDao.findByUsername(user.getUserName());
        if (u!=null){
            return false;
        }
        userDao.saveRegister(user);
        return true;
    }
    /**
     * 查询数据库是否有该用户名的方法
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return  userDao.findByUsername(username);
    }


}
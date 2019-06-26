package com.bbs.service;

import com.bbs.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户数据
     * @return
     */
    List<User> findAll();

    /**
     * 用户登录
     * @param userName
     * @param userPass
     * @return
     * @throws Exception
     */
    User login(String userName, String userPass) throws Exception;

    /**
     * 根据用户名和密码查找数据
     * @param userName
     * @param userPass
     * @return
     */
    User  findByUserNameAndPassword(String userName, String userPass);


    /**
     * 改变用户登录状态
     * @param userId
     * @param loginStatus
     */
    void updateLoginStatus(Integer loginStatus,Integer userId);

    //    用户注册
    boolean saveRegister(User user);

    User findByUsername(String username);
}

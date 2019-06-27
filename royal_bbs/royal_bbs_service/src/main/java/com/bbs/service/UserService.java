package com.bbs.service;

import com.bbs.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户数据
     * @return
     */
    List<User> findAll(Integer page,Integer size);



    /**
     * 根据用户名和密码查找数据
     * @param userName
     * @param userPass
     * @return
     */
    User  findByUserNameAndPassword(String userName, String userPass) throws Exception;


    /**
     * 改变用户登录状态
     * @param userId
     * @param loginStatus
     */
    void updateLoginStatus(Integer loginStatus,Integer userId);

    //    用户注册
    boolean saveRegister(User user);

    User findByUsername(String username);

    List<User> findByLoginStatus(Integer loginStatus);
}

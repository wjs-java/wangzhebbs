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
    User login(String userName, String userPass)throws Exception;
}

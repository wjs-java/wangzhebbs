package com.bbs.dao;

import com.bbs.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao{

    /**
     * 用户登录
     * @param userName
     * @param userPass
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_user_table where userName=#{userName} and userPass=#{userPass}")
    User login(@Param("userName")String userName,@Param("userPass")String userPass)throws Exception;

    /**
     * 查询所有用户数据
     * @return
     */
    @Select("select * from bbs_user_table")
    List<User> findAll();
    /**
     * 根据用户名和密码获取用户
     * @param userName
     * @param userPass
     * @return
     */
    @Select("select * from bbs_user_table where userName = #{userName} and userPass = #{userPass}")
    User findByUserNameAndPassword(String userName, String userPass);
}

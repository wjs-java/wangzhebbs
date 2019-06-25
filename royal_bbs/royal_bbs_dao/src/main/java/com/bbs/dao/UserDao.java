package com.bbs.dao;

import com.bbs.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
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

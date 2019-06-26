package com.bbs.dao;

import com.bbs.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    User findByUsernameAndUserPass(@Param("userName")String userName,@Param("userPass")String userPass)throws Exception;

    /**
     * 查询所有用户数据
     * @return
     */
    @Select("select * from bbs_user_table")
    List<User> findAll();


    @Update("update bbs_user_table set loginStatus=#{loginStatus} where userId=#{userId}")
    void updateLoginStatus( @Param("loginStatus")Integer loginStatus,@Param("userId")Integer userId);

//    注册功能
    /**
     * 保存用户注册信息的方法
     * @param user
     * @return
     */
    @Insert("insert into bbs_user_table(userName,userPass,email,picUrl,role,lastLoginTime,loginStatus,talkStatus,isupdating,updateStatus) values(#{userName},#{userPass},#{email},#{picUrl},#{role},#{lastLoginTime},#{loginStatus},#{talkStatus},#{isupdating},#{updateStatus})")
    void saveRegister(User user);


    /**
     * 查询数据库是否有该用户名的方法
     * @param username
     * @return
     */
    @Select("select *from bbs_user_table where userName = #{userName}")
    User findByUsername(String username);
}

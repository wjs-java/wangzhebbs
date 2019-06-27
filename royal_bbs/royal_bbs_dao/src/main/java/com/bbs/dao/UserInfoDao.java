package com.bbs.dao;

import com.bbs.domain.User;
import com.bbs.domain.ZoneApply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface UserInfoDao {
    /**
     * 更新邮箱，和头像
     * @param user
     */
    @Update("update bbs_user_table set email = #{email},picUrl=#{picUrl} where userId = #{userId}")
    void updateInfo(User user) throws Exception;

    /**
     * 更新密码
     * @param newPassword
     * @param userId
     */
    @Update("update bbs_user_table set userPass = #{arg0} where userId = #{arg1}")
    void updatePassword(String newPassword, String userId);

    /**
     * 提交新模块
     * @param zoneApply
     * @return
     */
    @Insert("insert into bbs_zoneapply_table (zoneName,userName,reason) values (#{zoneName},#{userName},#{reason})")
    void zoneApply(ZoneApply zoneApply);
}

package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ManagerDao {
    /**
     * 管理员校验
     * @param username
     * @return
     */
    @Select("select * from bbs_user_table where userName = #{username}")
    User ManagerLogin(String username);

    /**
     * 模糊查询帖子
     * @param title
     * @param sendername
     * @return
     */
    @Select("SELECT * FROM bbs_article_table WHERE title LIKE #{title} OR senderName LIKE #{sendername}")
    List<Article> findByLike(@Param("title") String title, @Param("sendername") String sendername);

    /**
     * 管理删除帖子
     * @param articleId
     */
    @Delete("delete from bbs_article_table where articleId = #{articleId}")
    void deleteArticle(Integer articleId);

    @Update("UPDATE bbs_article_table SET isTop=#{istop} WHERE articleId =#{articleId}")
    void topOrNo(Map map);

    /**
     * 升级用户
     * @param id
     */
    @Update("UPDATE bbs_user_table SET role = 2 WHERE userId=#{id}")
    void upgradeUser(Integer id);

    /**
     * 禁言用户
     */
    @Update("UPDATE bbs_user_table SET talkStatus = 1 WHERE userId=#{id}")
    void talkStatus(Integer id);

    /**
     * 模糊查询用户并分页
     * @param username
     * @param role
     * @return
     */
    @Select("SELECT * FROM bbs_user_table WHERE username LIKE #{username} OR role = #{role}")
    List<User> findByUserLike(@Param("username")String username, @Param("role")String role);
}

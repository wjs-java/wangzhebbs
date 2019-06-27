package com.bbs.dao;

import com.bbs.domain.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleDao {

    /**
     * 发帖功能
     * @param article
     */
    @Insert("insert into bbs_article_table (title, content, sendTime, senderName, isTop, replyCount, upvoteCount, browseCount, zoneId) values (#{title}, #{content}, #{sendTime}, #{senderName}, #{isTop}, #{replyCount}, #{upvoteCount}, #{browseCount}, #{zoneId})")
    void save(Article article);

    /**
     * 查询所有帖子
     * @return
     */
    @Select("select * from bbs_article_table")
    List<Article> findAll();


    /**
     * 根据ID查询帖子内容
     * @param articleId
     * @return
     */
    @Select("select * from bbs_article_table where articleId = #{articleId}")
    Article findById(Integer articleId);


    /**
     * 查询今日帖子
     * @return
     */
    @Select("SELECT COUNT(*) FROM bbs_article_table WHERE TO_DAYS(sendTime) = TO_DAYS(NOW())")
    Integer findTodayArticle();

    /**
     * 模糊查询
     */

    @Select("SELECT * FROM bbs_article_table WHERE title like #{msg}")
    List<Article> findByLike(String msg);

}

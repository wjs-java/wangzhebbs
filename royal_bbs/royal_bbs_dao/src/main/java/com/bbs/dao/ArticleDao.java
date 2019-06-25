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

}

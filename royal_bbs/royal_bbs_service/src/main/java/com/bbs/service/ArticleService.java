package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;

import java.util.List;

public interface ArticleService {

    /**
     * 发帖功能
     * @param article
     * @throws Exception
     */
    void save(Article article)throws Exception;

    /**
     * 查询所有帖子
     * @return
     */
    List<Article> findAll(Integer page,Integer size) throws Exception;

    /**
     * 查询今日帖子
     * @return
     */
    Integer findTodayArticle();

    /**
     * 模糊查询
     * @param msg
     * @return
     */
    List<Article> findByLike(String msg,Integer page,Integer size);
    /**
     * 根据ID查询帖子内容
     * @param articleId
     * @return
     * @throws Exception
     */
    Article findById(Integer articleId)throws Exception;

    /**
     * 点赞功能
     * @param article
     * @throws Exception
     */
    void addLike(Article article)throws Exception;

    /**
     * 通过Id查询点赞数
     * @param article
     * @return
     * @throws Exception
     */
    Integer findLikeById(Article article)throws Exception;



}

package com.bbs.service;

import com.bbs.domain.Article;

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
    List<Article> findAll();

}

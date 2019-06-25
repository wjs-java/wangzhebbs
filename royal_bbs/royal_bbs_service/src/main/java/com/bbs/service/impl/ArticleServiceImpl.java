package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 发帖功能
     * @param article
     * @throws Exception
     */
    @Override
    public void save(Article article) throws Exception {

        articleDao.save(article);

    }

    /**
     * 查询所有帖子
     * @return
     */
    @Override
    public List<Article> findAll() {

        return articleDao.findAll();
    }
}
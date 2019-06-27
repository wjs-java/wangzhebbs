package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.service.ArticleService;
import com.github.pagehelper.PageHelper;
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
    public List<Article> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);

        return articleDao.findAll();
    }

    /**
     * 查询今天帖子数
     * @return
     */
    @Override
    public Integer findTodayArticle() {

        return articleDao.findTodayArticle();
    }

    /**
     * 模糊查询
     * @param msg
     * @return
     */

    @Override
    public List<Article> findByLike(String msg,Integer page,Integer size) {
        PageHelper.startPage(page,size);
        return articleDao.findByLike(msg);
    }


    /**
     * 根据ID查询帖子内容
     * @param articleId
     * @return
     * @throws Exception
     */
    @Override
    public Article findById(Integer articleId) throws Exception {

        return articleDao.findById(articleId);
    }


}

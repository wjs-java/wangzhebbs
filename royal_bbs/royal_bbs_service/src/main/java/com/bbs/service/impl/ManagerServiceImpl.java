package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.dao.ManagerDao;
import com.bbs.domain.Article;
import com.bbs.domain.User;
import com.bbs.service.ArticleService;
import com.bbs.service.ManagerService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private ArticleDao articleDao;



    @Override
    public User ManagerLogin(String username) {
        return managerDao.ManagerLogin(username);
    }

    /**
     * 模糊查询帖子或用户发帖
     * @param title
     * @param sendername
     * @param page
     * @param size
     * @return
     */

    @Override
    public List<Article> findByLike(String title, String sendername, Integer page, Integer size) {

        if (title == "" && sendername =="" ){
            PageHelper.startPage(page,size);
            return articleDao.findAll();
        }

        PageHelper.startPage(page,size);
        return managerDao.findByLike(title,sendername);
    }

    /**
     * 删除帖子
     * @param articleId
     */

    @Override
    public void deleteArticle(Integer articleId) {
        managerDao.deleteArticle(articleId);
    }

    /**
     * 置顶或取消
     * @param articleId
     */

    @Override
    public void topOrNo(Integer istop,Integer articleId) {
        Map map = new HashMap();
        map.put("istop",istop);
        map.put("articleId",articleId);

        managerDao.topOrNo(map);
    }
    /**
     * 升级用户
     * @param id
     */
    @Override
    public void upgradeUser(Integer id) {
        managerDao.upgradeUser(id);
    }

    /**
     * 禁言用户
     */
    @Override
    public void talkStatus(Integer id) {
        managerDao.talkStatus(id);
    }

    /**
     *模糊查询用户分页
     * @param username
     * @param role
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<User> findByUserLike(String username, String role, Integer page, Integer size) {

        if (username == "" && role =="" ){
            PageHelper.startPage(page,size);
            return null;
        }

        PageHelper.startPage(page,size);
        return managerDao.findByUserLike(username,role);
    }


}

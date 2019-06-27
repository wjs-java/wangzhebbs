package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.User;

import java.util.List;

public interface ManagerService {
    /**
     * 管理员登录校验
     * @param username
     * @return
     */
    User ManagerLogin(String username);

    /**
     * 模糊查询帖子分页
     * @param title
     * @param sendername
     * @param page
     * @param size
     * @return
     */
    List<Article> findByLike(String title, String sendername,Integer page, Integer size);

    /**
     * 删除帖子操作
     * @param articleId
     */
    void deleteArticle(Integer articleId);

    /**
     * 置顶跟取消置顶
     * @param articleId
     */
    void topOrNo(Integer istop,Integer articleId);

    /**
     * 升级用户
     * @param id
     */
    void upgradeUser(Integer id);

    /**
     * 禁言用户
     */
    void talkStatus(Integer id);

    /**
     *模糊查询用户分页
     * @param username
     * @param role
     * @param page
     * @param size
     * @return
     */
    List<User> findByUserLike(String username, String role, Integer page, Integer size);
}

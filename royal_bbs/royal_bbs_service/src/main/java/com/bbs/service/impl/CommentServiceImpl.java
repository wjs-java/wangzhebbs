package com.bbs.service.impl;

import com.bbs.dao.CommentDao;
import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import com.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    /**
     * 保存评论帖子
     * @param
     * @throws Exception
     */
    @Override
    public void save(Comment comment, Integer articleId) throws Exception {

        commentDao.save(comment, articleId);
    }

    /**
     * 根据ID查询帖子所有评论
     * @param articleId
     * @return
     * @throws Exception
     */
    @Override
    public List<Comment> findAllById(Integer articleId) throws Exception {

        return commentDao.findAllById(articleId);
    }

    /**
     * 通过帖子编号查询所有评论编号
     * @param articleId
     * @return
     * @throws Exception
     */
    @Override
    public Integer[] findAllCommentId(Integer articleId) throws Exception {

        return commentDao.findAllCommentId(articleId);
    }

}

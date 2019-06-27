package com.bbs.service;

import com.bbs.domain.Comment;
import com.bbs.domain.Reply;

import java.util.List;

public interface CommentService {

    /**
     * 存储回复帖子
     * @throws Exception
     */
    void save(Comment comment, Integer articleId) throws Exception;


    /**
     * 根据ID查询帖子所有评论
     * @param articleId
     * @return
     */
    List<Comment> findAllById(Integer articleId)throws Exception;

    /**
     * 通过帖子编号查询所有评论编号
     * @param articleId
     * @return
     */
    Integer[] findAllCommentId(Integer articleId)throws Exception;


}

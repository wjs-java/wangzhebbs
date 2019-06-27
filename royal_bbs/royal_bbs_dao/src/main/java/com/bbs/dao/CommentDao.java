package com.bbs.dao;

import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentDao {

    /**
     * 保存评论贴
     * @param
     */
    @Insert("insert into bbs_comment_table (commentContent,commentTime,commentUserName,commentStatus,articleId) values (#{commentContent},#{commentTime},#{commentUserName},#{commentStatus},#{articleId})")
    void save(Comment comment) throws Exception;

    /**
     * 根据ID查询帖子所有评论
     * @param articleId
     * @return
     */
    @Select("select * from bbs_comment_table where articleId = #{articleId}")
    List<Comment> findAllById(Integer articleId);

    /**
     * 通过帖子编号查询所有评论编号
     * @param articleId
     * @return
     */
    @Select("select commentId from bbs_comment_table where articleId = #{articleId}")
    Integer[] findAllCommentId(Integer articleId);

}

package com.bbs.dao;

import com.bbs.domain.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReplyDao {

    @Insert("insert into bbs_reply_table(replyContent,replyTime,replyUserName, commentId) values(#{replyContent},#{replyTime},#{replyUserName}, #{commentId})")
    void saveReply(Reply reply);

    @Select("select * from bbs_reply_table where commentId = #{commentId}")
    List<Reply> findReplyAll(@Param("commentId")Integer commentId);
}

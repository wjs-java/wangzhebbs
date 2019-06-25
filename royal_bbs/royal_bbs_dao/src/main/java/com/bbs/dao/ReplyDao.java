package com.bbs.dao;

import com.bbs.domain.Reply;
import org.apache.ibatis.annotations.Insert;

/*wefuhwefoewfhnoweihnf了*/
public interface ReplyDao {

    @Insert("insert into bbs_reply_table(replyContent,replyTime,replyUserName) values(#{replyContent},#{replyTime},#{replyUserName})")
    void saveReply(Reply reply);
}

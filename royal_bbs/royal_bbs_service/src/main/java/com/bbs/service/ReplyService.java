package com.bbs.service;

import com.bbs.domain.Reply;

import java.util.List;
import java.util.Map;

public interface ReplyService {
    /**
     * 保存用户回复的内容
     * @param reply
     */
    void saveReply(Reply reply)throws Exception;

    /**
     * 根据ID查询回复表信息,查询出的结果为一个Map集合，键为评论ID，值为所有回复的List集合
     * @return
     */
    List<Reply> findReplyAll(Integer commentId)throws Exception;

}

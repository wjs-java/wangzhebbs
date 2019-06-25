package com.bbs.service;

import com.bbs.domain.Reply;

public interface ReplyService {
    /**
     * 保存用户回复的内容
     * @param reply 前台返回的回复内容
     */
    void saveReply(Reply reply);
}

package com.bbs.service.impl;

import com.bbs.dao.ReplyDao;
import com.bbs.domain.Reply;
import com.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDao replyDao;

    /**
     * 保存回复内容
     * @param reply 前台返回的回复内容
     */
    @Override
    public void saveReply(Reply reply) {
        replyDao.saveReply(reply);
    }
}

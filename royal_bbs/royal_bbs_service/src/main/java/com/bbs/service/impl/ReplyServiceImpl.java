package com.bbs.service.impl;

import com.bbs.dao.ReplyDao;
import com.bbs.domain.Reply;
import com.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据ID查询回复表信息,查询出的结果为一个Map集合，键为评论ID，值为所有回复的List集合
     * @return
     * @throws Exception
     */
    @Override
    public List<Reply> findReplyAll(Integer commentId) throws Exception {

        return replyDao.findReplyAll(commentId);
    }
}

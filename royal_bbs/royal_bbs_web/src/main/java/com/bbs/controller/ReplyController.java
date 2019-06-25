package com.bbs.controller;

import com.bbs.domain.Reply;
import com.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    /**
     * 存储回复内容
     * @param replyContent
     * @return
     */
    @RequestMapping("/saveReply.do")
    public String saveReply(String replyContent){
        Reply reply = new Reply();
        reply.setReplyContent(replyContent);
        reply.setReplyTime(new Date());
        reply.setReplyUserName("admin");
        replyService.saveReply(reply);
        return "test";
    }
}

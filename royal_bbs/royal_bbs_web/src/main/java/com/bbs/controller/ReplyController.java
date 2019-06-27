package com.bbs.controller;

import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import com.bbs.service.CommentService;
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

    @Autowired
    private CommentService commentService;

    /**
     * 存储回复内容
     * @param reply
     * @return
     */
    @RequestMapping("/saveReply.do")
    public String saveReply(Reply reply, Integer articleId) throws Exception {

        reply.setReplyTime(new Date());
        reply.setReplyUserName("admin");

        replyService.saveReply(reply);

        return  "redirect:/article/getArticle.do?articleId="+articleId;


       /* Reply reply = new Reply();
        reply.setReplyContent(replyContent);
        reply.setReplyTime(new Date());
        reply.setReplyUserName("admin");
        replyService.saveReply(reply);
        return "test";*/

    }
}

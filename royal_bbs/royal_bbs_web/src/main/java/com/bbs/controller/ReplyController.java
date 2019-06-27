package com.bbs.controller;

import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import com.bbs.domain.User;
import com.bbs.service.CommentService;
import com.bbs.service.ReplyService;
import com.bbs.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private WordService wordService;

    /**
     * 存储回复内容
     * @param reply
     * @return
     */
    @RequestMapping("/saveReply.do")
    public String saveReply(Reply reply, Integer articleId, HttpServletRequest request) throws Exception {

        User user = (User) request.getSession().getAttribute("user");
        String userName = user.getUserName();
        reply.setReplyTime(new Date());
        reply.setReplyUserName(userName);

        String replyContent = reply.getReplyContent();
        //查询所有的敏感词汇
        List<String> words = wordService.findAll();
        for (String word : words) {

            //字符串的长度
            int l = word.length();

            String s = "";
            //目的为了获取敏感词汇的个数，几个字兑换为几个*
            for (int i = 0; i < l; i++) {
                s += "*";
            }
            //判断标题中是否有敏感词汇
            if (replyContent.contains(word)) {
                //如果有，则把该敏感词汇逐字替换为*
                replyContent = replyContent.replace(word, s);
            }
        }
            reply.setReplyContent(replyContent);

            replyService.saveReply(reply);

        return  "redirect:/article/getArticle.do?articleId="+articleId;




    }
}

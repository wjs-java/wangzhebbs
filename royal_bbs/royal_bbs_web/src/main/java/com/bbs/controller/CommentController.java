package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import com.bbs.domain.User;
import com.bbs.service.ArticleService;
import com.bbs.service.CommentService;
import com.bbs.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private WordService wordService;

    @Autowired
    private ArticleService articleService;

    /**
     * 保存评论
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Comment comment, Integer articleId, HttpServletRequest request) throws Exception{

//        comment.setCommentContent(comment.getCommentContent());
        String commentContent = comment.getCommentContent();

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

            if(commentContent.contains(word)){

                commentContent = commentContent.replace(word, s);
            }

        }

        User user = (User) request.getSession().getAttribute("user");
        String userName = user.getUserName();

        comment.setCommentContent(commentContent);
        comment.setCommentUserName(userName);
        comment.setCommentTime(new Date());
//        comment.setArticleId(comment.getArticleId());
        comment.setCommentStatus(0);
        commentService.save(comment, articleId);


        return  "redirect:/article/getArticle.do?articleId="+comment.getArticleId();

        /*response.sendRedirect(request.getContextPath()+"/article/getArticle.do?articleId="+comment.getArticleId());*/

    }

}

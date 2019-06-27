package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import com.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 保存回复帖子
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Comment comment, HttpServletRequest request) throws Exception{

//        comment.setCommentContent(comment.getCommentContent());
        comment.setCommentUserName("admin");
        comment.setCommentTime(new Date());
//        comment.setArticleId(comment.getArticleId());
        comment.setCommentStatus(0);
        commentService.save(comment);


        return  "redirect:/article/getArticle.do?articleId="+comment.getArticleId();

        /*response.sendRedirect(request.getContextPath()+"/article/getArticle.do?articleId="+comment.getArticleId());*/

    }

    /**
     * 根据评论ID查询所有回复信息
     * @return
     */
    /*@RequestMapping("/findReplyById.do")
    public ModelAndView findReplyById(Article article) throws Exception {

        ModelAndView mv = new ModelAndView();
        //帖子编号
        Integer articleId = article.getArticleId();   //通过帖子编号可以获取该帖的所有评论ID (commentId)
        Integer[] commentIds = commentService.findByArId(articleId);
        for (Integer commentId : commentIds) {
            //通过每个commentId查询所有回复
            List<Reply> replies = commentService.findByComId(commentId);
            mv.addObject("replies", replies);
            mv.setViewName("getArticle");
        }

        return mv;
    }*/
}

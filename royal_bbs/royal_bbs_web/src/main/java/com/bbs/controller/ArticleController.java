package com.bbs.controller;

import com.bbs.domain.*;
import com.bbs.service.ArticleService;
import com.bbs.service.CommentService;
import com.bbs.service.ReplyService;
import com.bbs.service.WordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private WordService wordService;

    /**
     * 发帖功能
     * @param title
     * @param content
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(String title,String content, HttpServletRequest request) throws Exception {

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
            if(title.contains(word)){
                //如果有，则把该敏感词汇逐字替换为*
                title = title.replace(word, s);
            }

            ////判断帖子中是否有敏感词汇
            if(content.contains(word)){
                //如果有，则把该敏感词汇逐字替换为*
                content = content.replace(word, s);
            }
        }

        User user = (User) request.getSession().getAttribute("user");
        String userName = user.getUserName();

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setSendTime(new Date());
        article.setSenderName(userName);
        article.setIsTop(0);
        article.setReplyCount(0);


        article.setUpvoteCount(0);
        article.setBrowseCount(0);
        article.setZoneId(1);
        articleService.save(article);

        return "redirect:findAll.do";

    }



    /**
     * 查询所有帖子  修改成分页
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size, HttpServletRequest request) throws Exception{

        ModelAndView mv = new ModelAndView();

        List<Article> articleList = articleService.findAll(page,size);
        //查询今日帖子数
        Integer todayArticle = articleService.findTodayArticle();
        PageInfo pageInfo = new PageInfo(articleList);
        HttpSession session = request.getSession();
        session.setAttribute("todayArticle",todayArticle);

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("index-page-old");
        return mv;

    }

    /**
     * 模糊查询分页
     * @param msg
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByLike.do")
    public ModelAndView findByLike(String msg,
                                   @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                   @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {
        msg ="%"+msg+"%";
        ModelAndView mv = new ModelAndView();
        List<Article> byTitle = articleService.findByLike(msg,page,size);
        PageInfo pageInfo = new PageInfo(byTitle);
        mv.addObject("pageInfo",pageInfo);

        mv.setViewName("search-show");
        return mv;
    }

    /**
     * 根据ID查询帖子内容和该帖子的评论内容
     * @param articleId
     * @return
     */
    @RequestMapping("/getArticle.do")
    public ModelAndView getArticle(Integer articleId) throws Exception {

//        System.out.println(articleId);
        ModelAndView mv = new ModelAndView();

        //根据ID查询帖子内容
        Article article = articleService.findById(articleId);


        //根据ID查询所有评论
        List<Comment> comments = commentService.findAllById(articleId);

        //创建map集合，存储评论编号和所有回复集合
        Map<Integer, List<Reply>> map = new HashMap<>();

        for (Comment comment : comments) {

            Integer commentId = comment.getCommentId();
            //通过评论编号查询所有回复
            List<Reply> replies = replyService.findReplyAll(commentId);
            //如果所有回复的集合不为空，则把评论编号和回复集合存入map中
            if(replies != null && replies.size() != 0){

                map.put(commentId, replies);
            }
        }


        mv.addObject("map", map);

        mv.addObject("article", article);
        mv.addObject("comments", comments);
        mv.setViewName("getArticle");

        return mv;
    }



}

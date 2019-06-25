package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 发帖功能
     * @param title
     * @param content
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(String title,String content) throws Exception {

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setSendTime(new Date());
        article.setSenderName("李四");
        article.setIsTop(0);
        article.setReplyCount(0);
        article.setUpvoteCount(0);
        article.setBrowseCount(0);
        article.setZoneId(1);
        articleService.save(article);
        return "test";

    }

    /**
     * 查询所有帖子
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){

        ModelAndView mv = new ModelAndView();
        List<Article> articleList = articleService.findAll();
        mv.addObject("articleList", articleList);
        mv.setViewName("index-old");
        return mv;

    }

}

package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 查询所有帖子  修改成分页
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception{

        ModelAndView mv = new ModelAndView();

        List<Article> articleList = articleService.findAll(page,size);
        //查询今日帖子数
        Integer todayArticle = articleService.findTodayArticle();
        PageInfo pageInfo = new PageInfo(articleList);

        mv.addObject("pageInfo", pageInfo);
        mv.addObject("todayArticle",todayArticle);
        mv.setViewName("index-page-old");
        return mv;

    }

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
}

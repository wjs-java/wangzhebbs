package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.User;
import com.bbs.service.ArticleService;
import com.bbs.service.ManagerService;

import com.bbs.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    /**
     * 管理员登录校验
     * @param username
     * @param userpass
     * @return
     */
    @RequestMapping("/ManagerLogin.do")
    public ModelAndView ManagerLogin(@RequestParam(name = "username",required = true) String username,
                                     @RequestParam(name = "userpass",required = true) String userpass){
        ModelAndView  mv= new ModelAndView();

        User user =managerService.ManagerLogin(username);
        String managerError = "请使用管理员身份登录";
        if (user !=null){
            if (user.getRole() == 3){
                //如果权限为3就进行密码校验跳转页面
                if (userpass.equalsIgnoreCase(user.getUserPass())){
                    mv.setViewName("main");
                }else{
                    //错误向错误域中存入信息跳转登录页面
                    mv.addObject("error", managerError);
                    mv.setViewName("login");
                }
            }else {
                //错误向错误域中存入信息跳转登录页面
                mv.addObject("error", managerError);
                mv.setViewName("login");
            }
        }else {
            //错误向错误域中存入信息跳转登录页面
            mv.addObject("error", managerError);
            mv.setViewName("login");
        }

        return mv;
    }

    /**
     * 查询帖子信息
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Article> articleList = articleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(articleList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("ArticlePage");
        return mv;

    }

    /**
     * 模糊查询帖子分页
     * @param title
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByLike.do")
    public ModelAndView findByLike(String title,String sendername,
                                   @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                   @RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {



        if (title!=""){
            title ="%"+title+"%";
            sendername=null;
        }

        if ( sendername !=""&& sendername!=null ){
            sendername = "%"+sendername+"%";
        }

        ModelAndView mv = new ModelAndView();
        List<Article> byTitle = managerService.findByLike(title,sendername,page,size);
        PageInfo pageInfo = new PageInfo(byTitle);
        mv.addObject("pageInfo",pageInfo);

        mv.setViewName("ArticlePage");
        return mv;
    }

    /**
     * 删除帖子
     */

    @RequestMapping("/deleteArticle.do")
    protected String deleteArticle(@RequestParam(name = "id",required = true)Integer articleId){
        managerService.deleteArticle(articleId);
        return "redirect:findAll.do";
    }

    /**
     * 置顶取消置顶操作
     */
    @RequestMapping("/topOrNo.do")
    public String topOrNo(@RequestParam(name = "id",required = true)Integer articleId,
                          @RequestParam(name = "istop",required = true) Integer istop){
        managerService.topOrNo(istop,articleId);
        return "redirect:findAll.do";
    }

    /**
     * 查询所有用户并且分页
     */
    @RequestMapping("/findAllUser.do")
    public ModelAndView findAllUser(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                    @RequestParam(name = "size",required = true,defaultValue = "4") Integer size){

        ModelAndView mv = new ModelAndView();

        List<User> userList = userService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("UserPage");
        return mv;

    }

    /**
     * 更改用户权限
     */

    @RequestMapping("/upgradeUser")
    public String upgradeUser(@RequestParam(name = "id",required = true) Integer id){
        managerService.upgradeUser(id);
        return "redirect:findAllUser.do";
    }

    /**
     * 禁言用户
     */
    @RequestMapping("/talkStatus.do")
    public String talkStatus(@RequestParam(name = "id",required = true) Integer id){
        managerService.talkStatus(id);
        return "redirect:findAllUser.do";
    }

    /**
     * 模糊查询用户分页
     * @param username
     * @param role
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findByUserLike.do")
    public ModelAndView findByUserLike(String username,String role,
                                       @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                       @RequestParam(name = "size",required = true,defaultValue = "4") Integer size){





        ModelAndView mv = new ModelAndView();
        List<User> byUserLike = managerService.findByUserLike("%"+username+"%","%"+role+"%",page,size);
        PageInfo pageInfo = new PageInfo(byUserLike);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("UserPage");
        return mv;

    }

}

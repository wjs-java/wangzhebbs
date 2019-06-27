package com.bbs.controller;

import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<User> users = userService.findAll();
        mv.addObject("users",users);
        mv.setViewName("test");
        return mv;
    }

    /**
     * 用户登录
     * @param
     * @param
     * @return
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public User login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.findByUserNameAndPassword(user.getUserName(), user.getUserPass());
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",user1);

        if (user1 != null) {
            userService.updateLoginStatus(1,user1.getUserId());
            //登录成功
            request.getSession().setAttribute("user",user1);
            
        }

        return user1;
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        userService.updateLoginStatus(0,loginUser.getUserId());
        session.removeAttribute("user");
        return "redirect:"+request.getContextPath()+"/index.jsp";
    }


    /**
     * 查询数据库是否有该用户名的方法
     * @param username
     * @return
     */
    @RequestMapping("/findByUsername.do")
    @ResponseBody
    public String findByUsername(String username) throws Exception{
        if(userService.findByUsername(username) == null){
            return "true";
        }
        return "false";
    }

    //    用户注册
    /**
     * 保存用户注册信息的方法
     * @param user
     * @return
     */
    @RequestMapping("/saveRegister.do")
    @ResponseBody
    public String saveRegister(User user,HttpServletRequest request) {
        try {
            user.setRole(1);
            user.setLastLoginTime(new Date());
            user.setLoginStatus(1);
            user.setTalkStatus(0);
            user.setIsupdating(0);
            userService.saveRegister(user);
            login(user,request);

            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * 显示在线人数
     * @return
     */
    @RequestMapping("/onlineUser.do")
    @ResponseBody
    public List<User> onlineUser(){
        List<User> users = userService.findByLoginStatus(1);
        return users;
    }


}

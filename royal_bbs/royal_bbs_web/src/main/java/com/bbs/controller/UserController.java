package com.bbs.controller;

import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
     * @param userName
     * @param userPass
     * @return
     */
    @RequestMapping("/login.do")
    public ModelAndView login(String userName,String userPass){
        ModelAndView mv = new ModelAndView();
        User user = userService.findByUserNameAndPassword(userName,userPass);
        mv.addObject("loginUser",user);
        mv.setViewName("index");
        return mv;
    }
}

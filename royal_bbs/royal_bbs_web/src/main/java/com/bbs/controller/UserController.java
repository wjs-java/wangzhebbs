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
        User user1 = userService.login(user.getUserName(), user.getUserPass());
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",user1);

        if (user1 != null) {
            //登录成功
            request.getSession().setAttribute("user",user1);
        }
        return user1;
    }
}

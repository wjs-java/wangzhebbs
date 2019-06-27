package com.bbs.controller;

import com.bbs.domain.User;
import com.bbs.domain.ZoneApply;
import com.bbs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private HttpSession ServletActionContext;

    @RequestMapping("/updateInfo.do")
    public String updateInfo(User user,HttpServletRequest request) throws Exception{

        userInfoService.updateInfo(user);



        return "redirect:/jsp/userInfo.jsp";
    }

    @RequestMapping("/updatePassword.do")
    public String updatePassword(String newPassword, String userId,HttpServletRequest request){

        userInfoService.updatePassword(newPassword,userId);

        return "redirect:/jsp/userPwd.jsp";
    }

    /*@ResponseBody
    @RequestMapping("/upload")
    public Map<String,String> upload(MultipartFile file ){
        System.out.println("11111111");
        Map<String, String> map = new HashMap<>();
        try {

            String fname = file.getOriginalFilename();
            String path = ServletActionContext.getServletContext().getRealPath("/upload");
            File tfile = new File(path, fname);
            if (!tfile.getParentFile().exists()){
                tfile.getParentFile().mkdir();
            }
            file.transferTo(tfile);
            map.put("flag","true");
            map.put("piname",fname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }*/

    @RequestMapping("/zoneApply.do")
    public String zoneApply(ZoneApply zoneApply, HttpServletRequest request, HttpServletResponse response) throws Exception {


        userInfoService.zoneApply(zoneApply);

        return "redirect:/jsp/zoneApply.jsp";
    }
}

package cn.litten.web;

import cn.litten.domain.User;
import cn.litten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    //负责处理index.html的请求
    @RequestMapping(value = "/index.html")
    public String loginPage(){
        return "login";
    }

    //检查用户名和密码
    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginChek(HttpServletRequest request,LoginCommand loginCommand){
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUsername(),loginCommand.getPassword());

        if (!isValidUser){
            return new ModelAndView("login","error","用户名或密码错误！");
        }else {
            //如果用户存在就根据用户名字获得用户对象
            User user = userService.findUserByUsername(loginCommand.getUsername());
            //更新用户登录的最新ip和相关信息
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user",user);
            return new ModelAndView("main");
        }
    }
}

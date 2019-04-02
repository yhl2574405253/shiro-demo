package cn.et.demo02.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demo02")
public class ShiroController {

    @RequestMapping("login")
    public String login(String userName, String password){
        //获取当前的用户
        Subject currentUser =SecurityUtils.getSubject();

        //用户输入的用户名跟密码
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        try {
            currentUser.login( token );
            return "/LoginSuccess.jsp";
        } catch (UnknownAccountException uae) {
            System.out.println("用户名不存在:" + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            System.out.println("密码不正确!!");
        } catch (LockedAccountException lae) {
            System.out.println("用户已被锁定!!");
        }
        return "/LoginError.jsp";
    }
}

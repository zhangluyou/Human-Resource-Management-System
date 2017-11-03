package com.jointem.hrm.controller;

import com.jointem.hrm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * Created by dartagnan on 17/8/2.
 */
@Controller
@RequestMapping("/welcome")
public class LoginController {
    @Resource
    private UserService userService;
    @RequestMapping("/manage")
    public String manage() {
        return "manage";
    }
    @RequestMapping("/index")
    public String welcome() {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser && currentUser.isAuthenticated()) {
            return "manage";
        }
        return "welcome";
    }
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        String result = "login";
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
             result = login(currentUser, username, password);
        }
        return result;
    }

  

    private String login(Subject currentUser, String username, String password) {
        String result = "login";
        UsernamePasswordToken token = new UsernamePasswordToken(username,
                password);
        token.setRememberMe(false);
        try {
            currentUser.login(token);
            result = "redirect:manage";
        } catch (UnknownAccountException uae) {
            uae.printStackTrace();
            result = "redirect:index";
        } catch (IncorrectCredentialsException ice) {
            ice.printStackTrace();
            result = "redirect:index";
        } catch (LockedAccountException lae) {
            lae.printStackTrace();
            result = "redirect:index";
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            result = "redirect:index";
        }
        return result;
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout() {
//
//        Subject currentUser = SecurityUtils.getSubject();
//        String result = "welcome";
//        currentUser.logout();
//        return result;
//    }

    @RequestMapping(value = "/chklogin", method = RequestMethod.POST)
    @ResponseBody
    public String chkLogin() {

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            return "false";
        }

        return "true";
    }

  
}
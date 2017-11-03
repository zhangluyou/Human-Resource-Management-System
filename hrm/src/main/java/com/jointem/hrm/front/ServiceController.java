package com.jointem.hrm.front;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
@Controller
@RequestMapping(value="/mobile/oo")
public class ServiceController {

    @RequestMapping("/hello")
    public @ResponseBody String hello1() {
        return "hello";
                //+ param1[0] + param1[1] + param2;
    }

    @RequestMapping("/teo")
    public @ResponseBody String hello2() {
        return "teo";
        //+ param1[0] + param1[1] + param2;
    }

}

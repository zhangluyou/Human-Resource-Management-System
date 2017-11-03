package com.jointem.hrm.controller;

import com.alibaba.fastjson.JSON;
import com.jointem.hrm.common.JsonResult;
import com.jointem.hrm.common.MD5Tools;
import com.jointem.hrm.entity.Department1;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.TalentService;
import com.jointem.hrm.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dartagnan on 17/7/31.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private TalentService talentService;
    @RequiresPermissions("users:left")
    @RequestMapping("/left.do")
    public String left() {

        return "left";
    }

    @RequiresPermissions("users:top")
    @RequestMapping("/top.do")
    public String top(){
        return "top";
    }

    @RequiresPermissions("users:main")
    @RequestMapping("/mainfra.do")
    public String mainfra(){
        return "mainfra";
    }
    
    @RequestMapping("/manageDialog.do")
    public String manageDialog(){
        return "manageDialog";
    }
    
   
    
    
    @RequiresPermissions("users:verifyName")
    @RequestMapping("/verifyName")
    public @ResponseBody
    String verifyName(String name) {
        JsonResult jsonResult = new JsonResult();
        try {
            Users user = userService.setList1(name);
            if (user == null) {
                jsonResult.setCode("000001");
                jsonResult.setMsg("用户名可用");
            } else {
                jsonResult.setCode("000002");
                jsonResult.setMsg("用户名不可用");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode("000003");
            jsonResult.setMsg("查询失败");
        }
        return JSON.toJSONString(jsonResult);

    }
@RequiresPermissions("users:toAddUser")
    @RequestMapping("/toAddUser")
public String employment() {
    return "adduser";
}
    @RequestMapping(value= "/addUser",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(Users  params) {
        JsonResult jsonResult = new JsonResult();
        Format format = new SimpleDateFormat("yyyy");
        StringBuffer sb = new StringBuffer();
        try {
           // List<String> positionIdList=Arrays.asList(position.split(","));
        // Users users=JSON.parseObject(params,Users.class);
//           user.setName(new String(user.getName().getBytes("iso-8859-1"),"utf-8"));
//          user.setContent(new String(user.getContent().getBytes("iso-8859-1"),"utf-8"));
       int userId=userService.insertUser(params);
        sb.append("ZYT");
        sb.append(format.format(new Date()));
        sb.append(userId);
        String b = String.valueOf(sb);
        params.setJobId(b);
        userService.updateUser(params);
            jsonResult.setCode("000000");
            jsonResult.setMsg("添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setCode("000001");
            jsonResult.setMsg("添加不成功");
        }
        return JSON.toJSONString(jsonResult);
    }
@RequiresPermissions("users:toListUser")
    @RequestMapping("/toListUser")
    public String toListUser() {
        return "/listuser";
    }

    @RequestMapping(value = "/listUser",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toListUser(HttpServletRequest req) {
        String sort = req.getParameter("sort");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        String username = req.getParameter("username");
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        JsonResult jsonResult = new JsonResult();
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<Users> list = userService.selectUsers(sort, startTime, endTime, username, pageNum, pageSize);
        int count = userService.selectAllCount(sort, startTime, endTime, username);
        resMap.put("usersList", list);
        resMap.put("count", count);
        jsonResult.setData(resMap);
        jsonResult.setCode("0000000");
        jsonResult.setMsg("success");
        return jsonResult.toJson();
    }
@RequiresPermissions("user:modifyuser")
    @RequestMapping("/modifyuser.do")
    public String modifyUser(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
       Users user = userService.selectById(id);
        model.addAttribute("user", user);
        return "updateuser";
    }
@RequiresPermissions("user:deleteuser")

    @RequestMapping("/deleteuser.do") // 删除计划
    public String deleteUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.delectUser(id);
        return "redirect:toListUser";

    }
    @RequiresPermissions("user:modifyuser2")

    @RequestMapping("/modifyuser2.do")
    public String updateuser2(Model model, HttpServletRequest request, Users user) {
        user.setPassword(MD5Tools.MD5(user.getPassword()));
        userService.updateUser(user);
        return "redirect:toListUser";

        //  String link2 = toListUser(model);
        // return link2;
    }
    @RequiresPermissions("user:detailuserView")

    @RequestMapping("detailuserView.do")
    public String detailUserView(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Users user = userService.selectById(id);
        model.addAttribute("user", user);
        return "detailuser";
    }
    @RequiresPermissions("user:deleteAlluser")

    /**
     *
     * @param request
     * @param model
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteAlluser.do", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAllUser(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        JsonResult jsonResult=new JsonResult();
        userService.delectAllUser(list);
        jsonResult.setCode("111111");
      /* String link2 = toListUser(model,request,null,null);*/
        return jsonResult.toJson();
        //return link2;
    }
    @RequestMapping(value= "/assignjob",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String assignJob(){
        List<Department1> departments=userService.selectHeadquarters();
        JsonResult json=new JsonResult();
        json.setData(departments);
        return json.toJson();
    }

}

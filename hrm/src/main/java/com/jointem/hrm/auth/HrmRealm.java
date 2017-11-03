package com.jointem.hrm.auth;

/**
 * Created by dartagnan on 17/8/2.
 */
import com.jointem.hrm.common.MD5Tools;
import com.jointem.hrm.entity.Permissions;
import com.jointem.hrm.entity.Roles;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.provider.MD5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义的指定Shiro验证用户登录的类
 * @create
 * @author
 */
public class HrmRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) getAvailablePrincipal(principals);
        //我们可以通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Users user=userService.findUsersByName(username);
        
        //数据库查询：权限集合，角色集合
        Set<String> s = new HashSet<String>();
        Set<String> r = new HashSet<String>();
        for(Roles role:user.getRolesList())
        {
        	r.add(role.getDescription());
        	for(Permissions permisson:role.getPermissionsList())
        	{
        		s.add(permisson.getUrl());
        	}
        }
        info.setStringPermissions(s);
        info.setRoles(r);
        return info;
    }
    /**
     * 在这个方法中，进行身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        //用户名
        String username = (String) token.getPrincipal();
        //密码
        String password = new String((char[])token.getCredentials());

        //从数据库获取用户名密码进行匹配
        Users user = userService.getByUsername(username);
        if(!user.getUsername().equals(username)){
            throw new UnknownAccountException();
        }
        if(!user.getPassword().equals(MD5Tools.MD5(password))){
            throw new IncorrectCredentialsException();
        }
        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(username,password,getName());
        setSession("user", user);

        return aInfo;
    }

    /**
     * 保存登录名
     */
    private void setSession(Object key, Object value){
        Session session = getSession();
        System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
        if(null != session){
            session.setAttribute(key, value);
        }
    }

    private Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){

        }
        return null;
    }
}
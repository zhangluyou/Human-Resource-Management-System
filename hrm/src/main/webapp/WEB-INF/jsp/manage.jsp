<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>人力资源管理系统<% String username=(String)request.getAttribute("username"); %>您好，<%=username %></title>
    <style>
      .manageDialog{
        position:fixed;
      }
    </style>
</head>

<frameset rows="59,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="/hrm/users/top.do" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
    <frameset cols="213,*" frameborder="no" border="0" framespacing="0">
        <frame src="/hrm/users/left.do" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
        <frame src="/hrm/users/mainfra.do" name="mainFrame" id="mainFrame" title="mainFrame" />
    </frameset>
</frameset>
<noframes>
<body>
</body>
</noframes></html>


<%@page import="javax.swing.text.Document"%>
<%@page import="java.lang.*"%>
<%@ page import="org.aspectj.weaver.ast.Var"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@ page import="com.jointem.hrm.entity.*"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<title>职级管理</title>
	<meta charset="UTF-8">
		<link rel="stylesheet"
			href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
			<script
				src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
			<script
				src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
			<link rel="stylesheet"
				href="${pageContext.request.contextPath}/plug/ztree/css/demo.css"
				type="text/css"></link>
			<link rel="stylesheet"
				href="${pageContext.request.contextPath}/plug/ztree/css/zTreeStyle/zTreeStyle.css"
				type="text/css"></link>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="62"
				background="${pageContext.request.contextPath}/images/nav04.gif">&nbsp;</td>
		</tr>
	</table>
	<div class="container">
		<div>
			<select id="rankId">
				<option value="0" selected>档</option>
			</select> <select id="fileId">
				<option value="0" selected>级</option>"
			</select> <input id="salary" type="text" placeholder="请输入职级薪资"
				departmentId /> <input id="addPosition" type="button" value="添加" />
			<input id="delPosition" type="button" value="删除" /> <input
				id="setPosition" type="button" value="修改" />
		</div>
		<div id="demo"></div>
	</div>
</body>
		<script type="text/javascript"
				src="${pageContext.request.contextPath}/Js/jobManage.js"></script>
</html>
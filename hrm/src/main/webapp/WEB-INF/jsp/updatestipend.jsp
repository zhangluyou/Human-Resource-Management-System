<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.jointem.hrm.entity.Stipend"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人力资源管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style.css " type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/typem.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/js.js"></script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>
<body class="ContentBody">
<form name="stipendForm" method="post" action="updateStipend.do?action=updatestipend&pageNum=${pageNum }" onSubmit="return stipendValidate();" >
<div class="MainDiv">

<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">

  <tr>
  
      <th class="tablestyle_title" >薪金数据更新</th>
  </tr>
  <c:choose> <c:when test="${ empty stipend}"><tr><span class="red">无薪金信息</span></tr></c:when>
  <c:otherwise>
  <tr>
  
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
		<tr><td align="left">
			<!-- <input type="submit"value="更新" class="button"/>　 -->
			
			</td>
		</tr>
		<tr>
			<td width="100%">
				<fieldset style="height:100%;">
				<legend>薪金数据</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <input type="hidden" name="id" value="${stipend.id}"/>
					  <tr>
					    <td nowrap align="right" width="9%">员工姓名：</td>
					    <td width="36%"><input name="name" type="text" class="input" value="${stipend.name }"/>
						<span class="red">*</span></td>
					    <td width="12%"><div align="right">基本薪金：</div></td>
					    <td width="43%"><input name="basic" type="text" class="input" id="basic" value="${stipend.basic }" />
					      <span class="red">*</span></tr>
					  <tr>
					    <td nowrap align="right" width="9%">饭补：</td>
					    <td><input name="eat" type="text" class="input" id="eat" value="${stipend.eat }"/></td>
					    <td><div align="right">房补：</div></td>
					    <td><input name="house" type="text" class="input" id="house"  value="${stipend.house }"/></td>
					  </tr>
					  <tr>
					    <td nowrap align="right">全勤奖：</td>
					    <td><input name="duty" type="text" class="input" id="duty" value="${stipend.duty }"></td>
					    <td><div align="right">赋税：</div></td>
					    <td><input name="scot" type="text" class="input" id="scot"  value="${stipend.scot }"/></td>
					    </tr>
					  <tr>
					    <td nowrap align="right">额外补助：</td>
					    <td><input name="other" type="text" class="input" id="other"  value="${stipend.other }"/></td>
					    <td><div align="right">罚款：</div></td>
					    <td><input name="punishment" type="text" class="input" id="punishment"  value="${stipend.punishment }"></td>
					    </tr>
					  <tr>
					    <td nowrap align="right">发放时间：</td>
					     <td width="29%"><label for="starttime"></label> <input
						type="date" id="granttime" name="granttime" class="input"
						value=<fmt:formatDate value="${stipend.granttime}" pattern="yyyy-MM-dd"/> /></td>
				        <span class="red">*</span></td>
					    <td colspan="2"><div align="left">注：金钱单位（元/RMB）</div></td>
					    </tr>
					  </table>
				</fieldset>		
			</td>
		</tr>
		</c:otherwise></c:choose>
	<tr>
		<td colspan="2" align="center" height="50px">
		<input name="更新" type="submit" class="button" value="更新"/>　
		<input name="重置" type="reset" class="button" value="重置"/>
		<a href="listStipendView?pageNum=${pageNum }">
			<input type="button" value="返回" class="button"/></a>
		</td>
	</tr>
	
	</tabel>
</table>
</body>
</html>


<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="com.jointem.hrm.entity.Educate"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%-- <%@ page import="com.sanqing.po.Educate"%> --%>
<%@ page import="com.jointem.hrm.entity.Users"%>
<%@ page import="com.sanqing.tool.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>人力资源管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

<form name="educateForm" method="post" action="chooseStudents" onsubmit="return educateValidate();">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          	 <tr>
               <td height="20"><span class="newfont07">培训学员查看</span></td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="8" align="center" style="font-size:16px">培训学员列表</td>
                  </tr>
                 <tr bgcolor="#EEEEEE">
                                            <td width="4%" align="center" height="30">用户名</td>
                                            <td width="10%" align="center" height="30">真实姓名</td>

                                            <td width="10%" align="center">性别</td>
                                            <td width="10%" align="center">出生日期</td>
                                            <td width="10%" align="center">入职时间</td>
                                            
                                        </tr>
                                        </thead>
                                        <tbody id="usersTable">
                                        <c:choose>
                                            <c:when test="${empty students}">暂时没有用户信息</c:when>
                                            <c:otherwise>
                                                <c:forEach items="${students}" var="u">

                                                    <tr bgcolor="#FFFFFF" id="userDateFor">
                                                        <td height="22" align="center">
                                                            <c:out value="${u.username}"/>
                                                        </td>
                                                        <td height="22" align="center">
                                                            ${u.name }
                                                        </td>
                                                        <td height="22" align="center">
                                                            <c:choose>
                                                                <c:when test="${u.sex==1}">
                                                                    <c:out value="男"></c:out>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:out value="女"></c:out>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td height="22" align="center">
                                                            <fmt:formatDate value="${u.birthday }"
                                                                            pattern="yyyy-MM-dd"/>
                                                                              </td>
                                                        <td height="22" align="center">
                                                            <fmt:formatDate value="${u.createtime }"
                                                                            pattern="yyyy-MM-dd"/> </td>

                                                        
                                                        </tr>
                                                        </c:forEach>
                                                        </c:otherwise>
                                                        </c:choose>
            </table></td>        
        </tr>  
      </table>
          </td>
        </tr>
</table>
</td></tr>
</table>
  <tr><td >
 <a href="detaileducate.do?id=${id }&pageNum=${pageNum}&flag=${flag}">
 <input type="button" value="返回" class="button"/></a></td></tr>   
</form>


</body>
</html>
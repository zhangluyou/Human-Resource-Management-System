<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.jointem.hrm.entity.Educate"%>
<%-- <%@ page import="com.sanqing.tool.*"%> --%>
<%@ page import="java.text.SimpleDateFormat" %>
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
<c:choose>
 <c:when test="${empty educate}">该信息不存在！！</c:when>
 <c:otherwise>
<form name="educateForm" method="post" action="updateeducate.do?pageNum=${pageNum }&id=${educate.id }" onSubmit="return educateValidate();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >培训总结</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
		<!-- <tr><td align="left">
		<input type="submit"value="保存" class="button"/>　
			
		</td></tr> -->
	  	<input type="hidden" name="id" value="${educate.id }"/>
	  	<input type="hidden" name="educate" value="${educate }"/>
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>培训计划</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td nowrap align="right" width="11%">培训名称：</td>
					    <td colspan="3"><input name="name" type="text" class="input" value="${educate.name }" disabled/>
					    <span class="red">*</span></td>
					    </tr>
					  <tr>
					    <td nowrap align="right" width="11%">培训目的：</td>
					    <td colspan="3">
					    
					    <input type="text" name="purpose" class="input" value="${educate.purpose }" disabled/>
					    <span class="red">*</span></td>
					    </tr>
					  <tr>
					    <td nowrap align="right">培训开始时间：</td>
					    <td width="29%"><input type="date"  name="begintime" class="input" 
					    value=<fmt:formatDate
											value="${educate.begintime }" pattern="yyyy-MM-dd" />  disabled/>
					    	<span class="red">*</span></td>
					    <td width="18%">培训结束时间：</td>
					    <td width="42%"><input type="date"  name="endtime" class="input" 
					    value=<fmt:formatDate
											value="${educate.endtime }" pattern="yyyy-MM-dd" />  disabled/>
					    <span class="red">*</span></td>
					  </tr>
					  <tr>
					    <td nowrap align="right">讲师：</td>
					    <td><input type="text" name="teacher" class="input" value="${educate.teacher }" disabled/>
					    <span class="red">*</span></td>
					    <td>培训人员：</td>
					    <td><input type="text" name="student" class="input" value="${educate.student }" disabled/>
					    <span class="red">*</span>
						</td>
					  </tr>
					    
					  <tr>
					    <td width="11%" nowrap align="right">培训材料：</td>
					    <td colspan="3"><textarea name="datum" cols="100" rows="6" class="input" disabled><c:out value="${educate.datum }"></c:out></textarea></td>
					  </tr>
					  
					  <tr align="center">
					    <td width="11%" height="22" >培训效果：</td>
					    <td height="22" colspan="3" align="left" >
					   <input  type="radio" name="effect" value="好" checked/>好
					   <input  type="radio" name="effect" value="中" />中
					   <input  type="radio" name="effect" value="差" />差
					    </td>
					  </tr>
					  <tr align="center">
					    <td width="11%" height="22" >培训总结：</td>
					    <td height="22" colspan="3" align="left" ><textarea name="summarize" cols="50" rows="6"  class="input" >
                        <c:if test="${educate.summarize==null }">还未填写（请编辑）！</c:if>
                  <c:if test="${educate.summarize!=null }"><c:out value="${educate.summarize }"></c:out></c:if>
                       </textarea></td>
					  </tr>
					  </table>
			  <br />
				</fieldset>			</TD>
			
		</TR>
		</TABLE>
	 </td>
  </tr>
	<TR>
		<TD colspan="2" align="center" height="50px">
			<input name="提交" type="submit" class="button" value="保存"/>　
			<input name="重置" type="reset" class="button" value="重置"/>
			<a href="listEducateView?pageNum=${pageNum }">
			 <input type="button" value="返回" class="button" /></a>
		</TD>
	</TR>
</TABLE>	
</div>
</form>
</c:otherwise>
</c:choose>
</body>
</html>


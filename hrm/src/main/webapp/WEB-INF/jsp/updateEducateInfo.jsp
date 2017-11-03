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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/Js/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/Js/bootstrap.min.js"></script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
<script type="text/JavaScript">
	function selectedAll() {
		var allsel = document.getElementsByName("nameCheckbox");/* teleCheckbox为复选框的name属性值 */
		for (var i = 0; i < allsel.length; i++) {
			allsel[i].checked = !allsel[i].checked;
		}
	}
	function getValues() {
		document.educateForm2.name1.value = document.educateForm.name.value;
		document.educateForm2.teacher1.value = document.educateForm.teacher.value;
		document.educateForm2.purpose1.value = document.educateForm.purpose.value;
		document.educateForm2.datum1.value = document.educateForm.datum.value;
		document.educateForm2.begintime1.value = document.educateForm.begintime.value;
		document.educateForm2.endtime1.value = document.educateForm.endtime.value;
		document.educateForm2.id1.value=document.educateForm.id.value;
	}
	function addCompare(stu)
	{
		console.log(stu);
		//input框的内容
		var stuList=stu.split(",");
		console.log(stuList);
		//发送ajax请求
		var url="${pageContext.request.contextPath }/educate/getStudentsList";
		var params=
		{
		};
		$("#userListDiv").html("");
		$.post(url,params,function(result){
			
			$.each(result,function(n,value)
			{
				var content = "<tr bgcolor='#FFFFFF'>";
				content+="<td height='22' align='center'>";
				if($.inArray(value.username ,stuList)!=-1)
				{
					 content+="<input type='checkbox' name='nameCheckbox' value='"+value.username+"' checked='checked' ></input></td>"; 
				}
				 else
				 {
					 content+="<input type='checkbox' name='nameCheckbox' value='"+value.username+"'></input></td>"; 
				 }
				 content+="<td height='22' align='center'>"+value.username+"</td>";
				 content+="<td height='22' align='center'>"+value.name+"</td>";
				 content+="<td height='22' align='center'>"+value.id+"</td>";
				 content+="<td height='22' align='center'>"+value.id+"</td>";
				 if(value.sex==1)
				 {
					 content+="<td height='22' align='center'>男</td>";
				 }
				 else
				 {
					 content+="<td height='22' align='center'>女</td>";
				 
				 }
				 content+="</tr>";
				 $("#userListDiv").append(content);
			});
		},"json");


	}
</script>
</head>
<body class="ContentBody">
<form name="educateForm" method="post" action="updateeducate.do?id=${educate.id }&flag=5" onSubmit="return educateValidate();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >培训计划修改</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
	  	<input type="hidden" name="id" value="${educate.id }"/>
	  	<input type="hidden" name="educate" value="${educate }"/>
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>培训计划</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td nowrap align="right" width="11%">培训名称：</td>
					    <td colspan="3"><input name="name" type="text" class="input" value="${educate.name }" />
					    <span class="red">*</span></td>
					    </tr>
					  <tr>
					    <td nowrap align="right" width="11%">培训目的：</td>
					    <td colspan="3">
					    
					    <input type="text" name="purpose" class="input" value="${educate.purpose }"/>
					    <span class="red">*</span></td>
					    </tr>
					  <tr>
					    <td nowrap align="right">培训开始时间：</td>
					    <td width="29%"><input type="date"  name="begintime" class="input" value="<fmt:formatDate value="${educate.begintime}" pattern="yyyy-MM-dd"/>" />
					    	<span class="red">*</span></td>
					    <td width="18%">培训结束时间：</td>
					    <td width="42%"><input type="date"  name="endtime" class="input" value="<fmt:formatDate value="${educate.endtime}" pattern="yyyy-MM-dd"/>" />
					    <span class="red">*</span></td>
					  </tr>
					  <tr>
					    <td nowrap align="right">讲师：</td>
					    <td><input type="text" name="teacher" class="input" value="${educate.teacher }" />
					    <span class="red">*</span></td>
					    <td>培训人员：</td>
					    <td><input type="text" name="student" class="input" value="${educate.student }"readonly />
					     <a data-toggle="modal" data-target="#choose" type="button" class="button" onclick="addCompare('${educate.student}')"> 
						 <input type="button" value="添加" class="button" /> </a>
					    <span class="red">*</span>
						</td>
					  </tr>
					    
					  <tr>
					    <td width="11%" nowrap align="right">培训材料：</td>
					    <td colspan="3"><textarea name="datum" cols="100" rows="6" class="input" >${educate.datum }</textarea></td>
					  </tr>
					  
					  <tr align="center">
					    <td width="11%" height="22" >培训效果：</td>
					    <td height="22" colspan="3" align="left" ><input name="effect" type="text"  class="input" value="${educate.effect }" size="50" disabled/></td>
					  </tr>
					  <tr align="center">
					    <td width="11%" height="22" >培训总结：</td>
					    <td height="22" colspan="3" align="left" ><textarea name="summarize" cols="50" rows="6"  class="input"  disabled><c:out value="${educate.summarize}"></c:out></textarea></td>
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
</body>

<div class="modal fade" id="choose" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<form name="educateForm2" method="post" action="chooseStudents?flag=5&pageNum=${pageNum }"
			onsubmit="return educateValidate();">
			<input type="hidden" name="name1" />
			<input type="hidden" name="teacher1" />
			 <input type="hidden" name="datum1" />
			  <input type="hidden" name="student1" /> 
			  <input type="hidden" name="purpose1" /> 
			  <input type="hidden" name="begintime1" />
			   <input type="hidden" name="endtime1" />
			    <input type="hidden" name="id1" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><table id="subtree1" style="DISPLAY:" width="100%"
							border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><table width="95%" border="0" align="center"
										cellpadding="0" cellspacing="0">
										<tr>
											<td height="20"><span class="newfont07">选择员工</span></td>
										</tr>
										<tr>
											<td height="40" class="font42">
												<table width="100%" border="1" cellpadding="4"
													cellspacing="1" bgcolor="#464646" class="newfont03">
													<tr class="CTitle">
														<td height="22" colspan="8" align="center"
															style="font-size: 16px">员工选择列表</td>
													</tr>
													<tr bgcolor="#EEEEEE">
														<td height="22" align="center">全选/取消<br><input
																type="checkbox" id="sel" name="sel" value="yes"
																onclick="selectedAll()"></td>
														<td height="22" align="center">用户名</td>
														<td height="22" align="center">真实姓名</td>
														<td height="22" align="center">编号</td>
														<td height="22" align="center">性别</td>
														<td height="22" align="center">入职时间</td>
													</tr>
													<tbody id="userListDiv">
<!-- 													动态加载 -->
													</tbody>

								<%-- 	<c:forEach items="${list}" var="j" varStatus="status">
								  <c:forEach items="${st }" var="arr"> " cheched"
									<tr bgcolor="#FFFFFF">
									<td height="22" align="center"><input type="checkbox" name="nameCheckbox" value="${j.username }"
										<c:if test="${ st[status.index]==j.username}"><c:out value="checked"></c:out></c:if> />
											</td>
									</c:forEach>
										<td height="22" align="center">${j.username }</td>
									<td height="22" align="center">${j.name }</td>
										<td height="22" align="center"><c:out
										value="${j.id }"></c:out></td>
										<td height="22" align="center"><c:choose>
											<c:when test="${j.sex==1}">
										<c:out value="男"></c:out>
														</c:when>
														<c:otherwise>
												<c:out value="女"></c:out>
													</c:otherwise>
												</c:choose></td>
										<td height="22" align="center"><fmt:formatDate
										value="${j.createtime}" pattern="yyyy-MM-dd" /></td>
														</tr>
													</c:forEach> --%>
												</table>
											</td>
										</tr>
									</table></td>
							</tr>
						</table></td>
				</tr>
			</table>
			<tr>
				<td>
					<p onclick="getValues()">
						<input type="submit" value="确定" class="btn btn-danger btn-ok" />
					</p>
				</td>
				<td>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</td>
			</tr>
		</form>
	</div>
	
</html>


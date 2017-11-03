<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人力资源管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style.css " type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/typem.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Js/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/Js/jquery.js"></script>

	<script type="text/javascript">
        window.onload=function(){

            document.getElementById("username").onblur=function(){
                var name = $("#username").val();
                // console.info(name);
                $.ajax({
                    type : 'POST',
                    url : '${pageContext.request.contextPath}/welcome/verifyName?name='+name,
                    contentType: "application/json; charset=utf-8",
                    //data : {"name":name},
                    dataType : 'json',
                    success : function(data) {
                        if(data.code =="000001") {
                            document.getElementById("msg").innerHTML=data.msg;
                        }else if(data.code =="000002"){
                            document.getElementById("msg").innerHTML=data.msg;
                        }
                    }
                });

            }
        }
	</script>
	<script type="text/javascript">
        function userValidate() {
            var name = $("#username").val();
            var password = $("#password").val();
            var birthday=$("#birthday").val();
            if (name == null || "" ==name) {
                alert("用户名不能为空");
                return false;
            }

//            if (password == null || "" == password) {
//                alert("密码不能为空");
//                return false;
//            }
            if (birthday == null || "" == birthday) {
                alert("出生日期不能为空");
                return false;
            }


        }
	</script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
	<script type="text/javascript">
		function _tolist(){
            location.href ='/hrm/users/toListUser';
		}
	</script>
</head>
<body class="ContentBody">
<form name="userForm" method="post" action="modifyuser2.do" onSubmit="return userValidate();">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >人员信息更新</th>
  </tr>
  <input type="hidden" name="id" value=" <c:out value="${user.id}"/>"/>
  <tr>
    <td class="CPanel">
		
		<table width="90%" border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
		<tr><td align="left">
				<input type="button" value="返回" class="button" onclick="_tolist()"/>

		</td></tr>
		
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>人员信息</legend>
					  <table width="100%" border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td nowrap align="right" width="9%">用户名：</td>
					    <td width="36%"><input name="username" type="text" class="input" id="username" value=" <c:out value="${user.username}"/>">
						<span class="red">*</span><span id="msg"></span></td>
					    <td width="12%"><div align="right">登陆密码：</div></td>
					    <td width="43%"><input name="password" type="password" class="input" id="password" value=""/>
				        <span class="red">*</span></tr>
						  <tr><td width="12%"><div align="right">真实姓名：</div></td><td width="43%"><input name="name" type="text" class="input" id="name" value="${user.name}"/>
							  <span class="red">*</span></tr>
					  <tr>
					    <td nowrap align="right" width="9%">性别：</td>
					    <td><input name="sex" type="radio" value="1" > 男
							<input name="sex" type="radio" value="0" >女</td>
					    <td><div align="right">出生日期：</div></td>
					    <%--<td><input name="birthday" type="text" class="input" id="birthday" value="<%=StringUtil.notNull(DateUtil.parseToString(u.getBirthday(),DateUtil.yyyyMMdd))%>">--%>
				        <%--<span class="red">*</span></td>--%>
						  <td>
							  <input name="birthday" type="date" class="input" id="birthday" value=""/>
						 	 <span class="red">*</span>
						  </td>
					  </tr>
					  <tr>
					    <%--<td nowrap align="right">是否管理员：</td>--%>
					    <%--<td><input name="isadminhelp" type="checkbox" <%=new Byte("1").equals(u.getIsadmin())?"checked":""%>  onClick="javascript:adminChecked();" >--%>
						<%--<input type="hidden"  name="isadmin" value="<%=u.getIsadmin()%>"></td>--%>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					  </tr>
					  <tr>
					    <td nowrap align="right">人员简介：</td>
					    <td colspan="3"><textarea name="content" cols="100" rows="6" class="input" id="content"><c:out value="${user.content }" /></textarea></td>
					    </tr>
					  </table>
			 		 <br />
				</fieldset>
				</TD>
			
		</TR>
		</TABLE>
	 </td>
  </tr>

		<TR>
			<TD colspan="2" align="center" height="50px">
			<input type="submit" class="button" value="更新"/>　
			
			<input type="reset" class="button" value="重置"/></TD>
		</TR>
	</TABLE>
</div>
</form>
</body>
</html>


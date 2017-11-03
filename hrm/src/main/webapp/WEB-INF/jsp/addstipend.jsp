<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
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
<form name="stipendForm" method="post" action="modifystipend.do"  >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >薪资固定部分设置</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
		<tr><td align="left">&nbsp;&nbsp;
		<!-- <input type="submit"value="保存" class="button"/>　
			
			<input type="reset" value="返回" class="button"/> -->
		</td></tr>
		
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>薪金数据</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr> <td nowrap align="right" width="9%">职位：</td>
					  <td height="22" colspan="3" align="left" >
					   <input  type="radio" name="position" value="高级Java工程师" checked/>高级Java工程师
					   <input  type="radio" name="position" value="UI设计师" />UI设计师
					   <input  type="radio" name="position" value="H5工程师" />H5工程师
					    </td></tr>
						<tr>
					    <td width="12%"><div align="right">职务基本工资：</div></td>
					    <td width="43%"><input name="basic" type="text" class="input" id="basic" />
					      <span class="red">*</span>
					      <td width="12%"><div align="right">职级工资：</div></td>
					    <td width="43%"><input name="level" type="text" class="input" id="basic" />
					      <span class="red">*</span>
					      </tr>
					  <tr>
					    <td nowrap align="right" width="9%">饭补：</td>
					    <td><input name="eat" type="text" class="input" id="eat"></td>
					    <td><div align="right">房补：</div></td>
					    <td><input name="house" type="text" class="input" id="house"></td>
					  </tr>
					   <tr>
					    <td nowrap align="right">通讯补贴：</td>
					    <td><input name="phone_call" type="text" class="input" id="phone_call"></td>
					    <td><div align="right">交通补贴：</div></td>
					    <td><input name="traffic" type="text" class="input" id="traffic"></td>
					    </tr>
					   <tr>
					    <td nowrap align="right">保密费：</td>
					    <td><input name="secret" type="text" class="input" id="secret"></td>
					    </tr>
					  <tr>
					    <td nowrap align="right">选择月份：</td>
					     <td width="29%"><label for="granttime"></label> <input
						type="month" id="granttime" name="granttime" class="input"
						 /><span class="red">*</span></td>
				        </td>
					    <td colspan="2"><div align="left">注：金钱单位（元/RMB）</div></td>
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
			<input name="重置" type="reset" class="button" value="重置"/></TD>
		</TR>
		</TABLE>	
</div>
</form>
</body>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>人力资源管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="css/style.css "
	type="text/css" media="all" />
<script type="text/javascript" src="Js/typem.js"></script>
<script type="text/javascript" src="Js/js.js"></script>
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>
</head>
<body class="ContentBody">
	<form name="jobForm" method="post" action="modifyjob.do?action=addjob"
		onSubmit="return jobValidate();">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">应聘信息录入</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width: 80%" align="center">
							<tr>
								<td align="left"><a href=""><input type="submit" value="返回"
									class="button" /></input></a>
								</td>
							</tr>
							<from>
							<TR>
								<TD width="100%" height="300px">
									<fieldset style="height: 100%;">
										<legend>应聘信息</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											<tr height="130px">
												<td nowrap align="right" width="9%">姓名：</td>
												<td width="36%"><input name="name" type="text"
													class="input"> <span class="red">*</span></td>
												<td width="12%"><div align="right">性别：</div></td>
												<td width="43%"><input name="sex" type="radio"
													value="男" checked>男 <input name="sex" type="radio"
														value="女">女 
											</tr>
											<tr height="100px">
												<td><div align="right">职位：</div></td>
												<td><input name="job" type="text" class="input">
														<span class="red">*</span></td>
											</tr>
											<tr height="70px">
												<td width="9%" nowrap align="right">简历：</td>
												<td colspan="3"><input type="file" name="uploadFile" />
													<br /> <input type="submit" value="上传" />
											</tr>
										</table>
									</fieldset>
								</TD>

							</TR>
							</from>

						</TABLE>
					</td>
				</tr>
				<TR>
					<TD colspan="2" align="center" height="50px"><input name="提交"
						type="submit" class="button" value="保存" /> <input name="重置"
						type="reset" class="button" value="重置" /></TD>
				</TR>
			</TABLE>
		</div>
	</form>
</body>
</html>

